package vista;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Carlos Lara
 */
public class DocumentoPanel extends javax.swing.JFrame {

    public DocumentoPanel() {
        initComponents();
        rutaDirectorio = mostrarDialogoSeleccionarCarpeta().getAbsolutePath();
        inicializarListaCarpetas();
    }

    String rutaDirectorio;

    public void actualizarTablaDocumentos() {
        DefaultTableModel modeloTabla = (DefaultTableModel) jTable1.getModel();
        modeloTabla.setRowCount(0);

        File directorioDocumentos = new File(rutaDirectorio);

        if (directorioDocumentos.exists() && directorioDocumentos.isDirectory()) {
            File[] archivos = directorioDocumentos.listFiles();

            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    String nombre = archivo.getName();
                    String tipoArchivo = obtenerTipoArchivo(archivo.getName());
                    Date fecha = new Date(archivo.lastModified());
                    long peso = archivo.length();
                    String pesoLegible = obtenerTamanioLegible(peso);

                    Object[] fila = {nombre, tipoArchivo, fecha, pesoLegible};
                    modeloTabla.addRow(fila);
                }
            }
        }
    }

    public String obtenerTipoArchivo(String nombreArchivo) {
        int indexPunto = nombreArchivo.lastIndexOf(".");
        if (indexPunto > 0 && indexPunto < nombreArchivo.length() - 1) {
            return nombreArchivo.substring(indexPunto + 1).toUpperCase();
        }
        return "";
    }

    private String obtenerTamanioLegible(long bytes) {
        int unidad = 1024;
        if (bytes < unidad) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unidad));
        char pre = "KMGTPE".charAt(exp - 1);
        return String.format("%.1f %sB", bytes / Math.pow(unidad, exp), pre);
    }

    private java.io.File mostrarDialogoSeleccionarCarpeta() {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione la carpeta que desea administrar.", "Selección de Carpeta", JOptionPane.INFORMATION_MESSAGE);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int seleccion = fileChooser.showDialog(this, "Seleccionar carpeta");

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            java.io.File carpetaSeleccionada = fileChooser.getSelectedFile();
            return carpetaSeleccionada;
        } else {
            System.out.println("Selección cancelada");
            return null;
        }
    }

    private void abrirArchivo(java.io.File archivo) {
        try {
            Desktop.getDesktop().open(archivo);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inicializarListaCarpetas() {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        jList1.setModel(modeloLista);

        modeloLista.addElement(rutaDirectorio);
        actualizarTablaDocumentos();

        jList1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String carpetaSeleccionada = jList1.getSelectedValue();

                    rutaDirectorio = carpetaSeleccionada;

                    actualizarTablaDocumentos();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subirDoc = new javax.swing.JButton();
        descargarDoc = new javax.swing.JButton();
        eliminarDoc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAbrir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        btnCarpeta = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        subirDoc.setText("Subir");
        subirDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subirDocActionPerformed(evt);
            }
        });

        descargarDoc.setText("Descargar");
        descargarDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descargarDocActionPerformed(evt);
            }
        });

        eliminarDoc.setText("Eliminar");
        eliminarDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarDocActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Tipo", "Fecha", "Peso"
            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Nombre");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("Tipo");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Fecha");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Peso");
        }

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Buscar:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("v1.0");

        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel3.setText("Gestor documental - GIA Gomez Ito y Asociados S.C. ");

        jLabel4.setText("Perfil: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Invitado");

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jList1);

        btnCarpeta.setText("Añadir carpeta");
        btnCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarpetaActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCarpeta, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(subirDoc)
                        .addGap(18, 18, 18)
                        .addComponent(descargarDoc)
                        .addGap(18, 18, 18)
                        .addComponent(eliminarDoc)
                        .addGap(18, 18, 18)
                        .addComponent(btnAbrir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(19, 19, 19)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subirDoc)
                    .addComponent(descargarDoc)
                    .addComponent(eliminarDoc)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscar)
                    .addComponent(btnAbrir)
                    .addComponent(btnCarpeta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String terminoBusqueda = txtBuscar.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) jTable1.getModel());
        jTable1.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> rowFilter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                for (int i = 0; i < entry.getValueCount(); i++) {
                    Object value = entry.getValue(i);
                    if (value != null && value.toString().toLowerCase().contains(terminoBusqueda)) {
                        return true;
                    }
                }
                return false;
            }
        };
    sorter.setRowFilter(rowFilter);    }//GEN-LAST:event_btnBuscarActionPerformed

    private void descargarDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descargarDocActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();

        if (filaSeleccionada != -1) {
            String nombreArchivo = jTable1.getValueAt(filaSeleccionada, 0).toString();
            String rutaArchivo = rutaDirectorio + File.separator + nombreArchivo;

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(nombreArchivo));

            int resultado = fileChooser.showSaveDialog(this);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File destino = fileChooser.getSelectedFile();

                try {
                    Files.copy(new File(rutaArchivo).toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }    }//GEN-LAST:event_descargarDocActionPerformed

    private void eliminarDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarDocActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();

        if (filaSeleccionada != -1) {
            String nombreArchivo = jTable1.getValueAt(filaSeleccionada, 0).toString();
            String rutaArchivo = rutaDirectorio + File.separator + nombreArchivo;

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar el archivo?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                File archivoEliminar = new File(rutaArchivo);

                if (archivoEliminar.exists()) {
                    if (archivoEliminar.delete()) {
                        actualizarTablaDocumentos();
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo eliminar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        }    }//GEN-LAST:event_eliminarDocActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed


    }//GEN-LAST:event_txtBuscarActionPerformed

    private void subirDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subirDocActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();

            String directorioDestino = rutaDirectorio + File.separator;

            try {
                File destino = new File(directorioDestino, archivoSeleccionado.getName());
                Files.copy(archivoSeleccionado.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                actualizarTablaDocumentos();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_subirDocActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombreArchivo = (String) jTable1.getValueAt(filaSeleccionada, 0);

            java.io.File archivo = new java.io.File(rutaDirectorio, nombreArchivo);

            abrirArchivo(archivo);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un archivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarpetaActionPerformed
        java.io.File carpetaSeleccionada = mostrarDialogoSeleccionarCarpeta();

        if (carpetaSeleccionada != null) {
            String rutaCarpeta = carpetaSeleccionada.getAbsolutePath();

            DefaultListModel<String> modeloLista = (DefaultListModel<String>) jList1.getModel();

            modeloLista.addElement(rutaCarpeta);
            rutaDirectorio = rutaCarpeta;
            jList1.setSelectedValue(rutaCarpeta, true);

            actualizarTablaDocumentos();
        }    }//GEN-LAST:event_btnCarpetaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DocumentoPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DocumentoPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DocumentoPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DocumentoPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DocumentoPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCarpeta;
    private javax.swing.JButton descargarDoc;
    private javax.swing.JButton eliminarDoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton subirDoc;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
