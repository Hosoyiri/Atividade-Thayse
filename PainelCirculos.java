import javax.swing.*;
import java.awt.*;

/**
 * Painel customizado do .
 * Desenha um semáforo horizontal moderno usando paintComponent.
 */
public class PainelCirculos extends JPanel {

    private SemaforoLogica logica;

    private final Color FUNDO = new Color(235, 238, 245);
    private final Color PAINEL = new Color(35, 39, 47);
    private final Color BORDA = new Color(80, 86, 100);

    private final Color APAGADO = new Color(75, 78, 88);

    private final Color VERMELHO = new Color(235, 64, 52);
    private final Color AMARELO = new Color(245, 190, 45);
    private final Color VERDE = new Color(46, 204, 113);

    public PainelCirculos(SemaforoLogica logica) {
        this.logica = logica;
        setPreferredSize(new Dimension(520, 260));
        setBackground(FUNDO);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int larguraPainel = 420;
        int alturaPainel = 130;
        int xPainel = (getWidth() - larguraPainel) / 2;
        int yPainel = 45;

        // Sombra
        g2.setColor(new Color(0, 0, 0, 35));
        g2.fillRoundRect(xPainel + 8, yPainel + 10, larguraPainel, alturaPainel, 35, 35);

        // Corpo do semáforo horizontal
        g2.setColor(PAINEL);
        g2.fillRoundRect(xPainel, yPainel, larguraPainel, alturaPainel, 35, 35);

        // Borda
        g2.setColor(BORDA);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(xPainel, yPainel, larguraPainel, alturaPainel, 35, 35);

        int diametro = 82;
        int yCirculo = yPainel + 24;

        int xVermelho = xPainel + 35;
        int xAmarelo = xPainel + 169;
        int xVerde = xPainel + 303;

        int estadoAtual = logica.getPosicaoAtual();

        Color corVermelho = estadoAtual == 0 ? VERMELHO : APAGADO;
        Color corAmarelo = estadoAtual == 1 ? AMARELO : APAGADO;
        Color corVerde = estadoAtual == 2 ? VERDE : APAGADO;

        desenharLampada(g2, xVermelho, yCirculo, diametro, corVermelho, estadoAtual == 0);
        desenharLampada(g2, xAmarelo, yCirculo, diametro, corAmarelo, estadoAtual == 1);
        desenharLampada(g2, xVerde, yCirculo, diametro, corVerde, estadoAtual == 2);

        // Textos abaixo dos círculos
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.setColor(new Color(50, 55, 65));

        desenharTextoCentralizado(g2, "VERMELHO", xVermelho, yPainel + 165, diametro);
        desenharTextoCentralizado(g2, "AMARELO", xAmarelo, yPainel + 165, diametro);
        desenharTextoCentralizado(g2, "VERDE", xVerde, yPainel + 165, diametro);
    }

    private void desenharLampada(Graphics2D g2, int x, int y, int diametro, Color cor, boolean acesa) {
        if (acesa) {
            // Brilho externo da luz acesa
            g2.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), 70));
            g2.fillOval(x - 12, y - 12, diametro + 24, diametro + 24);
        }

        // Fundo preto da cavidade
        g2.setColor(new Color(15, 16, 20));
        g2.fillOval(x - 5, y - 5, diametro + 10, diametro + 10);

        // Círculo principal
        g2.setColor(cor);
        g2.fillOval(x, y, diametro, diametro);

        // Reflexo
        if (acesa) {
            g2.setColor(new Color(255, 255, 255, 100));
            g2.fillOval(x + 18, y + 14, 22, 16);
        }

        // Borda
        g2.setColor(new Color(10, 10, 10));
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(x, y, diametro, diametro);
    }

    private void desenharTextoCentralizado(Graphics2D g2, String texto, int x, int y, int largura) {
        FontMetrics fm = g2.getFontMetrics();
        int larguraTexto = fm.stringWidth(texto);
        int xTexto = x + (largura - larguraTexto) / 2;
        g2.drawString(texto, xTexto, y);
    }
}