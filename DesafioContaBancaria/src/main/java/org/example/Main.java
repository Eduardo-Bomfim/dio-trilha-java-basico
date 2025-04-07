package org.example;

import org.example.model.Cliente;
import org.example.model.Conta;
import org.example.service.ContaCorrente;
import org.example.service.ContaPoupanca;

public class Main {

    public static void main(String[] args) {
        Cliente venilton = new Cliente();
        venilton.setNome("Venilton");

        Conta cc = new ContaCorrente(venilton);
        Conta poupanca = new ContaPoupanca(venilton);

        System.out.println("=== Operações Bancárias ===");

        System.out.println("Depositando R$100 na conta corrente...");
        cc.depositar(100);
        cc.imprimirExtrato();

        System.out.println("Transferindo R$100 para a conta poupança...");
        cc.transferir(100, poupanca);

        System.out.println("Extrato após transferência:");
        cc.imprimirExtrato();
        poupanca.imprimirExtrato();
    }
}