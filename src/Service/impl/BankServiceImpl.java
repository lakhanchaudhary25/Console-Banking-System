package Service.impl;

import Repository.BankRepo;
import Service.BankServices;
import UnChecked_Exception.AccountNotFoundException;
import UnChecked_Exception.FailedTransferException;
import entities.BankAccount;
import entities.CurrentAccount;
import entities.SavingAccount;
import entities.Transaction;


import java.util.List;


import static entities.Type.*;

public class BankServiceImpl implements BankServices {

    // 1. The declaration (Compiler is nervous here...)
    private final BankRepo bankRepo;
    // 2. The Constructor Injection (Compiler is happy here!)
    public BankServiceImpl( BankRepo bankRepo) {
        this.bankRepo = bankRepo; // The final variable is now locked in.
    }

    @Override
    public void transfer(String toId, String fromId, double amount) {
        // 1. Fetch the Sender Object (Account A)
        BankAccount sender = bankRepo.findById(fromId)
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found!"));
        // 2. Fetch the Receiver Object (Account B)
        BankAccount receiver = bankRepo.findById(toId)
                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found!"));
        final double previousBalance= sender.getBalance();
        sender.withdraw(amount);
        try {

           receiver.deposit(amount);
            Transaction transactionGo= new Transaction(DEBIT, fromId,amount);
            Transaction transactionIn= new Transaction(CREDIT, toId,amount);
            sender.addTransaction(transactionGo);
            receiver.addTransaction(transactionIn);

            } catch (Exception e) {
            sender.deposit(amount);
            throw new FailedTransferException("Transfer failed! Refunded $" + amount + " to sender.");
        }
    }


    @Override
    public void openAccount(String name, double initialDeposit, long phoneNumber, String type) {

        switch (type){
            case "Savings"->{
                BankAccount account= new SavingAccount(name,initialDeposit,phoneNumber, type );
                bankRepo.save(account);
            } case "Current"->{
                BankAccount account= new CurrentAccount(name,initialDeposit,phoneNumber, type );
                bankRepo.save(account);
            }
            default -> throw new IllegalArgumentException("Invalid account type!");
        }

        }


    @Override
    public List<Transaction> getTransactions(String accountNumber) {
        BankAccount account = bankRepo.findById(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found!"));
        return account.getTransaction();

    }

    @Override
    public List<BankAccount> getAllAccounts() {
        return bankRepo.findAll();
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        BankAccount bankAccount=bankRepo.findById(accountNumber)
        .orElseThrow(() -> new AccountNotFoundException("Account not found!"));

        bankAccount.deposit(amount);
        Transaction in = new Transaction(DEPOSIT,accountNumber,amount);
        bankAccount.addTransaction(in);

    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        BankAccount bankAccount=bankRepo.findById(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found!"));

        bankAccount.withdraw(amount);
        Transaction out = new Transaction(WITHDRAW,accountNumber,amount);
        bankAccount.addTransaction(out);
    }
}
