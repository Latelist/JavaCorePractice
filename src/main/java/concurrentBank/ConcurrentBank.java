package concurrentBank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class ConcurrentBank {
    private final ArrayList<BankAccount> accounts;

    public ConcurrentBank() {
        this.accounts = new ArrayList<>();
    }

    public BankAccount createAccount(BigDecimal initialBalance) {
        BankAccount bankAccount = new BankAccount(initialBalance);
        synchronized (accounts) {
            accounts.add(bankAccount);
            System.out.println("Создан акканут: " + bankAccount.getiD() + ". Начальный баланс: " + bankAccount.getBalance());
        }
        return bankAccount;
    }

    public void transfer(UUID iDa, UUID iDb, BigDecimal amount){
        BankAccount fromAccount = findAccountById(iDa);
        BankAccount toAccount = findAccountById(iDb);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Не нашёлся аккаунт");
        }

        BankAccount firstLock = fromAccount.getiD().compareTo(toAccount.getiD()) < 0 ? fromAccount : toAccount;
        BankAccount secondLock = fromAccount.getiD().compareTo(toAccount.getiD()) < 0 ? toAccount : fromAccount;

        synchronized (firstLock) {
            synchronized (secondLock) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            }
        }
    }

    public BigDecimal getTotalBalance(){
        synchronized (accounts) {
            return accounts.stream()
                    .map(BankAccount::getBalance)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    private BankAccount findAccountById(UUID iD) {
        synchronized (accounts) {
            return accounts.stream()
                    .filter(account -> account.getiD().equals(iD))
                    .findFirst()
                    .orElse(null);
        }
    }
}
