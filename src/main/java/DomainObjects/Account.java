package DomainObjects;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private String pass;
    private int saldo;
    private List<String> transactions;

//region Constructor & Getters
    public Account(String name, String pass, int saldo) {
        this.name = name;
        this.pass = pass;
        this.saldo = saldo;
        this.transactions = new ArrayList<>();
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

    public void transfer(int amt, Account toacc) {
        this.withdraw(amt);
        toacc.insert(amt);
        transactions.add("Overførsel på " + amt + " kr. til \"" + toacc.getName() + "\"");
    }
}
