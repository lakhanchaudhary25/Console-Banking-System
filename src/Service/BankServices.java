package Service;



import entities.BankAccount;
import entities.Transaction;

import java.util.List;


public interface BankServices {

    public void transfer(String toId, String fromId , double amount);
    public void openAccount(String name, double initialDeposit, long phoneNumber, String type);
    public List<Transaction> getTransactions(String accountNumber);
    List<BankAccount> getAllAccounts();
    public void deposit(String accountNumber,double amount);
    public void withdraw(String accountNumber,double amount);


}
