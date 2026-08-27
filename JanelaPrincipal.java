import javax.swing.*;
import java.awt.*;

/**
 * Janela principal
 */
public class JanelaPrincipal extends JFrame {

    private SemaforoLogica logica;
    private PainelCirculos painelCirculos;
    private JLabel labelEstado;
    private JLabel labelDescricao;

    public JanelaPrincipal() {
        logica = new SemaforoLogica();
        montarJanela();
    }

    private void montarJanela() {
        setTitle("Semaforo Digital - ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Color fundo = new Color(235, 238, 245);
        Color azulEscuro = new Color(30, 44, 72);

        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBackground(fundo);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Cabeçalho
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(azulEscuro);
        cabecalho.setBorder(BorderFactory.createEmptyBorder(18, 22, 18, 22));

        JLabel titulo = new JLabel("PAINEL DE CONTROLE DO SEMAFORO");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Simulacao com Java Swing");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(190, 205, 230));

        JPanel textosCabecalho = new JPanel();
        textosCabecalho.setLayout(new BoxLayout(textosCabecalho, BoxLayout.Y_AXIS));
        textosCabecalho.setBackground(azulEscuro);
        textosCabecalho.add(titulo);
        textosCabecalho.add(Box.createRigidArea(new Dimension(0, 4)));
        textosCabecalho.add(subtitulo);

        cabecalho.add(textosCabecalho, BorderLayout.WEST);

        painelPrincipal.add(cabecalho, BorderLayout.NORTH);

        // Centro com semáforo horizontal
        painelCirculos = new PainelCirculos(logica);
        painelPrincipal.add(painelCirculos, BorderLayout.CENTER);

        // Painel lateral de informações
        JPanel painelStatus = new JPanel();
        painelStatus.setLayout(new BoxLayout(painelStatus, BoxLayout.Y_AXIS));
        painelStatus.setBackground(Color.WHITE);
        painelStatus.setPreferredSize(new Dimension(220, 260));
        painelStatus.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 225)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel tituloStatus = new JLabel("Estado atual");
        tituloStatus.setFont(new Font("Arial", Font.BOLD, 16));
        tituloStatus.setForeground(new Color(70, 75, 85));
        tituloStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelEstado = new JLabel(logica.getEstadoAtual());
        labelEstado.setFont(new Font("Arial", Font.BOLD, 28));
        labelEstado.setForeground(corDoEstado(logica.getEstadoAtual()));
        labelEstado.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelDescricao = new JLabel(mensagemDoEstado(logica.getEstadoAtual()));
        labelDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
        labelDescricao.setForeground(new Color(90, 95, 105));
        labelDescricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botaoAvancar = new JButton("Avancar estado");
        botaoAvancar.setFont(new Font("Arial", Font.BOLD, 15));
        botaoAvancar.setBackground(new Color(30, 44, 72));
        botaoAvancar.setForeground(Color.WHITE);
        botaoAvancar.setFocusPainted(false);
        botaoAvancar.setBorderPainted(false);
        botaoAvancar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoAvancar.setMaximumSize(new Dimension(180, 42));
        botaoAvancar.setAlignmentX(Component.CENTER_ALIGNMENT);

        /*
         * ActionListener sem regra de negócio.
         * Ele apenas chama a lógica e atualiza a interface.
         */
        botaoAvancar.addActionListener(e -> {
            logica.proximoEstado();
            atualizarTela();
        });

        painelStatus.add(tituloStatus);
        painelStatus.add(Box.createRigidArea(new Dimension(0, 18)));
        painelStatus.add(labelEstado);
        painelStatus.add(Box.createRigidArea(new Dimension(0, 8)));
        painelStatus.add(labelDescricao);
        painelStatus.add(Box.createVerticalGlue());
        painelStatus.add(botaoAvancar);

        painelPrincipal.add(painelStatus, BorderLayout.EAST);

        add(painelPrincipal);

        pack();
        setLocationRelativeTo(null);
    }

    private void atualizarTela() {
        String estado = logica.getEstadoAtual();

        labelEstado.setText(estado);
        labelEstado.setForeground(corDoEstado(estado));

        labelDescricao.setText(mensagemDoEstado(estado));

        painelCirculos.repaint();
    }

    private Color corDoEstado(String estado) {
        if (estado.equals("VERMELHO")) {
            return new Color(220, 50, 50);
        }

        if (estado.equals("AMARELO")) {
            return new Color(210, 155, 20);
        }

        if (estado.equals("VERDE")) {
            return new Color(35, 170, 90);
        }

        return Color.BLACK;
    }

    private String mensagemDoEstado(String estado) {
        if (estado.equals("VERMELHO")) {
            return "Pare";
        }

        if (estado.equals("AMARELO")) {
            return "Atencao";
        }

        if (estado.equals("VERDE")) {
            return "Siga";
        }

        return "";
    }
}