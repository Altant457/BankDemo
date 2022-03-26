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

    public List<String> getTransactions() {
        return transactions;
    }
//endregion

    public int insert(int amt) {
        if (amt < 0) {
            return saldo;
        }
        saldo += amt;
        transactions.add(amt + " kr. indsat");
        return saldo;
    }

    public int withdraw(int amt) {
        if (amt < 0 || amt > saldo) {
            return saldo;
        }
        saldo -= amt;
        transactions.add(amt + " kr. hævet");
        return saldo;
    }

    public void transfer(int amt, Account otherAccount, boolean isSender) { // this is cursed
        if (isSender) { // the sender executes this part
            saldo -= amt;
            otherAccount.transfer(amt, this, false);
            transactions.add(0, "Overførsel på " + amt + " kr. til \"" + otherAccount.getName() + "\"");
        } else { // the recipient executes this part
            saldo += amt;
            transactions.add(0, "Overførsel på " + amt + " kr. fra \"" + otherAccount.getName() + "\"");
        }
    }
}
