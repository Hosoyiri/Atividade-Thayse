import java.util.ArrayList;

/**
 * Classe responsável pela lógica do semáforo.
 * Usa ArrayList para guardar os estados possíveis.
 */
public class SemaforoLogica {

    // Usando ArrayList conforme sugerido como alternativa
    private ArrayList<String> listaEstados;
    private int posicaoAtual;

    public SemaforoLogica() {
        listaEstados = new ArrayList<>();
        listaEstados.add("VERMELHO");
        listaEstados.add("AMARELO");
        listaEstados.add("VERDE");
        posicaoAtual = 0;
    }

    public String getEstadoAtual() {
        return listaEstados.get(posicaoAtual);
    }

    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public void proximoEstado() {
        posicaoAtual = (posicaoAtual + 1) % listaEstados.size();
    }

    // Teste da lógica antes de qualquer Swing
    public static void main(String[] args) {
        SemaforoLogica s = new SemaforoLogica();

        System.out.println("=== Teste da Lógica do Semáforo ===");
        System.out.println("Posição 0: " + s.getEstadoAtual());

        s.proximoEstado();
        System.out.println("Posição 1: " + s.getEstadoAtual());

        s.proximoEstado();
        System.out.println("Posição 2: " + s.getEstadoAtual());

        s.proximoEstado();
        System.out.println("Voltou ao início: " + s.getEstadoAtual());
    }
}