import java.util.Scanner;

class Conta {
    private String titular;
    private String numeroConta;
    protected double saldo;

    public Conta(String titular, String numeroConta, double saldo) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0) {
            if (this.saldo >= valor) {
                this.saldo -= valor;
                System.out.println("Saque realizado: " + String.format("%.2f", valor));
                System.out.println("Saldo Atual: " + String.format("%.2f", this.saldo));
                return true;
            } else {
                System.out.println("Saque invalido: Saldo insuficiente");
                System.out.println("Saldo Atual: " + String.format("%.2f", this.saldo));
                return false;
            }
        }
        return false;
    }
}

class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    public ContaCorrente(String titular, String numeroConta, double saldo, double limiteChequeEspecial) {
        super(titular, numeroConta, saldo);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0) {
            if (this.saldo + this.limiteChequeEspecial >= valor) {
                this.saldo -= valor;
                System.out.println("Saque realizado: " + String.format("%.2f", valor));
                System.out.println("Saldo Atual: " + String.format("%.2f", this.saldo));
                return true;
            } else {
                System.out.println("Saque invalido: Excede limite");
                System.out.println("Saldo Atual: " + String.format("%.2f", this.saldo));
                return false;
            }
        }
        return false;
    }
}

class ContaPoupanca extends Conta {
    public ContaPoupanca(String titular, String numeroConta, double saldo) {
        super(titular, numeroConta, saldo);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0) {
            if (this.saldo >= valor) {
                this.saldo -= valor;
                System.out.println("Saque realizado: " + String.format("%.2f", valor));
                System.out.println("Saldo Atual: " + String.format("%.2f", this.saldo));
                return true;
            } else {
                System.out.println("Saque invalido: Saldo insuficiente");
                System.out.println("Saldo Atual: " + String.format("%.2f", this.saldo));
                return false;
            }
        }
        return false;
    }
}

public class SistemaBancario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String tipoConta = scanner.nextLine();
        String titular = scanner.nextLine();
        String numeroConta = scanner.nextLine();
        double saldoInicial = scanner.nextDouble();

        Conta conta = null;

        if (tipoConta.equalsIgnoreCase("Corrente")) {
            double limiteChequeEspecial = scanner.nextDouble();
            scanner.nextLine(); // Consumir a quebra de linha
            conta = new ContaCorrente(titular, numeroConta, saldoInicial, limiteChequeEspecial);
        } else if (tipoConta.equalsIgnoreCase("Poupanca")) {
            scanner.nextLine(); // Consumir a quebra de linha
            conta = new ContaPoupanca(titular, numeroConta, saldoInicial);
        } else {
            scanner.close();
            return;
        }

        double valorSaque = scanner.nextDouble();
        conta.sacar(valorSaque);

        scanner.close();
    }
}