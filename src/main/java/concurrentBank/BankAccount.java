package concurrentBank;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccount {
    private UUID iD;
    private BigDecimal balance;

    public BankAccount(BigDecimal balance) {
        iD = UUID.randomUUID();
        this.balance = balance;
    }

    public synchronized void deposit(BigDecimal amount) {
       balance = balance.add(amount);
        System.out.println("На счёт " + iD + " добавлена сумма: " + amount);
        System.out.println("Остаток на счёте: " + balance);
        System.out.println();
    }

    public synchronized void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
        System.out.println("Со счёта " + iD + " снята сумма: " + amount);
        System.out.println("Остаток на счёте: " + balance);
        System.out.println();
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }

    public UUID getiD() {
        return iD;
    }
}
