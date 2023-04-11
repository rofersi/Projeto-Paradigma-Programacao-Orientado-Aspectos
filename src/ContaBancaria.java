public abstract class ContaBancaria {
    protected double saldo;

    public void depositar(double valor) {
        saldo += valor;
    }

    public void consultarSaldo() {
        System.out.println("Saldo: " + saldo);
    }

    public abstract void sacar(double valor);
}

public aspect VerificarSaldo {
    pointcut sacar() : execution(* ContaBancaria.sacar(double));

    before(double valor) : sacar() && args(valor) {
        if (saldo - valor < 0) {
            throw new SaldoInsuficienteException();
        }
    }

    private double ContaBancaria.saldo() {
        return saldo;
    }
}