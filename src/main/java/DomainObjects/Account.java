package DomainObjects;

public class Account {
    private String name;
    private String pass;
    private int saldo;

//region Constructor & Getters
    public Account(String name, String pass, int saldo) {
        this.name = name;
        this.pass = pass;
        this.saldo = saldo;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public int getSaldo() {
        return saldo;
    }

//endregion

    public int insert(int amt) {
        if (amt < 0) {
            return saldo;
        }
        saldo += amt;
        return saldo;
    }

    public int withdraw(int amt) {
        if (amt < 0 || amt > saldo) {
            return saldo;
        }
        saldo -= amt;
        return saldo;
    }
}
