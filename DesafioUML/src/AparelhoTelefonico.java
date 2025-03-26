public class AparelhoTelefonico{
    
    private String numero;
    private StatusChamada status; 

    public AparelhoTelefonico() {
        this.numero = null;
        this.status = StatusChamada.INATIVO;
    }

    public String ligar(String numero) {
        if (numero == null || numero.isEmpty()) {
            return "Número inválido!";
        }
        
        this.numero = numero;
        this.status = StatusChamada.CHAMANDO;
        return "Ligando para " + numero + "...";
    }

    public void atender() {
        if (status == StatusChamada.CHAMANDO) {
            status = StatusChamada.EM_CHAMADA;
            System.out.println("Chamada atendida. Conversando com " + numero + "...");
        } else {
            System.out.println("Nenhuma chamada para atender.");
        }
    }

    public void iniciarCorreioVoz() {
        if (status == StatusChamada.INATIVO) {
            System.out.println("Nenhuma chamada em andamento. Acessando o correio de voz...");
        } else {
            System.out.println("Não é possível acessar o correio de voz durante uma chamada.");
        }
    }

    public String getNumero() {
        return numero;
    }

    public StatusChamada getStatus() {
        return status;
    }
}