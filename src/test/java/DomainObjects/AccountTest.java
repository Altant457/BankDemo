package DomainObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account = new Account("John", "1234", 100);
    Account toacc = new Account("Jane", "5678", 100);

    @Test
    void getters() {
        assertEquals("John", account.getName());
        assertEquals("1234", account.getPass());
        assertEquals(100, account.getSaldo());
        assertEquals("[]", account.getTransactions().toString());
    }

    @Test
    void insert() {
        assertEquals(200, account.insert(100));
    }

    @Test
    void insertNegative() {
        assertEquals(100, account.insert(-100));
    }

    @Test
    void withdraw() {
        assertEquals(0, account.withdraw(100));
    }

    @Test
    void withdrawOverMax() {
        assertEquals(100, account.withdraw(200));
    }

    @Test
    void withdrawNegative() {
        assertEquals(100, account.withdraw(-100));
    }

    @Test
    void transfer() {
        account.transfer(100, toacc, true);
        assertEquals(0, account.getSaldo());
        assertEquals(200, toacc.getSaldo());
    }

    @Test
    void changePass() {
        assertEquals("1234", account.getPass());
        account.setPass("4321");
        assertEquals("4321", account.getPass());
    }
}