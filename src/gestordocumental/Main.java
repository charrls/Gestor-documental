package GestorDocumental;

import vista.DocumentoPanel;

public class Main {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DocumentoPanel documentoPanel = new DocumentoPanel();
                documentoPanel.actualizarTablaDocumentos();
                documentoPanel.setVisible(true);
            }
        });
    }
}
