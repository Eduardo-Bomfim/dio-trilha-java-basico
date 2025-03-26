public class NavegadorInternet {
    
    private String paginaAtual;
    private int numeroAbas;

    public NavegadorInternet() {
        this.paginaAtual = "Página inicial";
        this.numeroAbas = 1; // Sempre começa com uma aba aberta
    }

    public void exibirPagina(String url) {
        if (url == null || url.isEmpty()) {
            System.out.println("URL inválida! Insira um endereço válido.");
        } else {
            this.paginaAtual = url;
            System.out.println("Exibindo página: " + paginaAtual);
        }
    }

    public void adicionarNovaAba() {
        numeroAbas++;
        System.out.println("Nova aba adicionada. Total de abas abertas: " + numeroAbas);
    }

    public void atualizarPagina() {
        System.out.println("Página " + paginaAtual + " recarregada.");
    }

    public String getPaginaAtual() {
        return paginaAtual;
    }

    public int getNumeroAbas() {
        return numeroAbas;
    }
}
