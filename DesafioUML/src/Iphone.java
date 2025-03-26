public class Iphone {
    public static void main(String[] args) {
    
        ReprodutorMusical reprodutorMusical = new ReprodutorMusical();
        AparelhoTelefonico aparelhoTelefonico = new AparelhoTelefonico();
        NavegadorInternet navegadorInternet = new NavegadorInternet();

        System.out.println("\nTestando o Reprodutor Musical:");
        reprodutorMusical.selecionarMusica("Imagine - John Lennon");
        reprodutorMusical.tocar();
        reprodutorMusical.pausar();

        System.out.println("\nTestando o Aparelho Telefônico:");
        System.out.println(aparelhoTelefonico.ligar("11999999999"));
        aparelhoTelefonico.atender();
        aparelhoTelefonico.iniciarCorreioVoz();

        System.out.println("\nTestando o Navegador de Internet:");
        navegadorInternet.exibirPagina("www.google.com");
        navegadorInternet.adicionarNovaAba();
        navegadorInternet.atualizarPagina();
    }
}
