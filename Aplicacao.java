import javax.swing.SwingUtilities;

/**
 * Ponto de entrada.
 * Inicia a aplicação na Event Dispatch Thread com invokeLater().
 */
public class Aplicacao {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal janela = new JanelaPrincipal();
            janela.setVisible(true);
        });
    }
}