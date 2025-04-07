package org.example.model;

import org.example.repository.IConta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class Conta implements IConta {


    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected Cliente cliente;
    protected int numero;
    protected double saldo;
    protected int agencia;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino){
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    public void imprimirInfosComuns() {
        System.out.printf("Titular: %s%n", this.cliente.getNome());
        System.out.printf("Agencia: %d%n", this.agencia);
        System.out.printf("Numero: %d%n", this.numero);
        System.out.printf("Saldo: %.2f%n", this.saldo);
    }
}
