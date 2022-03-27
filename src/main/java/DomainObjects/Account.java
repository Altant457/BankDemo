package DomainObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {
    private String masterName;
    private String masterPass;
    private Map<User, Integer> users = new HashMap<>();
    private int saldo;
    private List<String> transactions;

//region Constructor, Getters & Setters
    public Account(User user, int saldo) {
        this.users.put(user, 1); // 1 == master, 0 == shared
        this.masterName = user.getName();
        this.masterPass = user.getPass();
        this.saldo = saldo;
        this.transactions = new ArrayList<>();
    }

    public Map<User, Integer> getUsers() {
        return users;
    }

    public String getMasterName() {
        return masterName;
    }

    public String getMasterPass() {
        return masterPass;
    }

    public void setMasterPass(String masterPass) {
        this.masterPass = masterPass;
    }

    public int getSaldo() {
        return saldo;
    }

    public List<String> getTransactions() {
        return transactions;
    }
//endregion

    public void addUserToAccount(User user) {
        users.put(user, 0);
    }

    public User getUserByName(String name) {
        for (User user : users.keySet()) {
            if (user.getName().equals(name)) {
                return user; // user exists, return user
            }
        }
        return null; // user does not exist
    }

    public int insert(int amt) {
        if (amt < 0) {
            return saldo;
        }
        saldo += amt;
        transactions.add(0, amt + " kr. indsat");
        return saldo;
    }

    public int withdraw(int amt) {
        if (amt < 0 || amt > saldo) {
            return saldo;
        }
        saldo -= amt;
        transactions.add(0, amt + " kr. hævet");
        return saldo;
    }

    public void transfer(int amt, Account otherAccount, boolean isSender) { // this is cursed
        if (isSender) { // the sender executes this part
            saldo -= amt;
            otherAccount.transfer(amt, this, false);
            transactions.add(0, "Overførsel på " + amt + " kr. til \"" + otherAccount.getMasterName() + "\"");
        } else { // the recipient executes this part
            saldo += amt;
            transactions.add(0, "Overførsel på " + amt + " kr. fra \"" + otherAccount.getMasterName() + "\"");
        }
    }
}
