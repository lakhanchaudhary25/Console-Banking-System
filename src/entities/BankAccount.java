package entities;

import UnChecked_Exception.InsufficientFundsException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public  abstract class BankAccount {
    private final String accountNumber;
    private double balance;
    private final String customerId;
    private static int nextID=1;
    private final String type;
    private final String customerName;

    List<Transaction> transactionList=new ArrayList<>();

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }
    public List<Transaction> getTransaction(){
           return transactionList;
    }
    public BankAccount(double initialDeposit, String type,String customerName) {

        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        this.customerId= UUID.randomUUID().toString();
        this.accountNumber=String.format("AC%06d",nextID);
        nextID++;
        this.type=type;
        this.balance = initialDeposit;
        this.customerName=customerName;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        this.balance += amount;


    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive");
        if (amount > this.balance) throw new InsufficientFundsException("Insufficient funds");
        this.balance -= amount;
    }

    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerId() {
        return customerId;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


}
