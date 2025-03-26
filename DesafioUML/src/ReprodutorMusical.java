public class ReprodutorMusical {
    
    private String status;
    private String musicaAtual;

    public ReprodutorMusical() {
        this.status = "Parado"; 
        this.musicaAtual = "";
    }

    public void tocar() {
        if (musicaAtual.isEmpty()) {
            System.out.println("Nenhuma música selecionada! Selecione uma música primeiro.");
        } else {
            status = "Tocando";
            System.out.println("Música tocando: " + musicaAtual);
        }
    }

    public void pausar() {
        if (status.equals("Tocando")) {
            status = "Pausado";
            System.out.println("Música pausada.");
        } else {
            System.out.println("Não há música tocando para ser pausada.");
        }
    }

    public void selecionarMusica(String musica) {
        this.musicaAtual = musica;
        System.out.printf("A música \"%s\" foi selecionada e está pronta para tocar.\n", musicaAtual);
    }

    public String getStatus() {
        return status;
    }

    public String getMusicaAtual() {
        return musicaAtual;
    }
}
