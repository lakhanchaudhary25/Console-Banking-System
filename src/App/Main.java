package App;
import Repository.BankRepo;
import Service.BankServices;
import Service.impl.BankServiceImpl;
import entities.BankAccount;
import entities.Transaction;

import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        BankRepo database=new BankRepo();
        BankServiceImpl bankService=new BankServiceImpl(database);
            Scanner sc= new Scanner(System.in);
            System.out.println("WELCOME TO CONSOLE BANK");
            System.out.println("Choose the Transaction :");
            boolean redirect= true;
            while(redirect){

                System.out.println("""
CHOOSE:
1. OPEN ACCOUNT
2. DEPOSIT
3. WITHDRAW
4. TRANSFER
5. ACCOUNT STATEMENT
6. LIST ACCOUNTS
7. SEARCH ACCOUNT BY NAME
8. EXIT
""");
                System.out.println("Enter your choice: ");
                String choice= sc.nextLine().trim();

                switch(choice){

                    case "8"-> redirect=false;
                    case "1"-> openAccount(sc,bankService);
                    case "2"-> deposit(sc,bankService);
                    case "3"-> withdraw(sc,bankService);
                    case "4"-> transfer(sc,bankService);
                    case "5"-> accountStatement(sc,bankService);
                    case "6"-> listAccounts(bankService);
                    default -> System.out.println("invalid input");

                }
            }
        }
    private static void listAccounts(BankServices bankServices) {
        List<BankAccount> allAccounts = bankServices.getAllAccounts();

        if (allAccounts.isEmpty()) {
            System.out.println("No accounts found in the bank.");
            return;
        }

        System.out.println("=== ALL BANK ACCOUNTS ===");
        // Using an enhanced for-loop to iterate through the list
        for (BankAccount account : allAccounts) {
            // We use String.format for that beautiful aligned output we practiced earlier
            System.out.println(String.format("Acc No: %-16s | Name: %-25s | Type: %-10s | Balance: $%.2f ",
                    account.getAccountNumber(),
                    account.getCustomerName(),
                    account.getType(),
                    account.getBalance()
                   ));
        }
        System.out.println("=========================");

    }

    private static void accountStatement(Scanner sc, BankServices bankServices) {
        System.out.println("Enter the account number:");
        String ac= sc.nextLine();
        System.out.println("=======TRANSACTIONS==========");
        for(Transaction t: bankServices.getTransactions(ac)){
            String formattedRow = String.format("Date: %-19s | Type: %-10s | Amount: $%,10.2f",
                    t.getDateTime().toString(),
                    t.getType(),
                    t.getAmount());
            System.out.println(formattedRow);
        }
    }

    private static void withdraw(Scanner sc,BankServices bankServices) {
        System.out.println("Enter the Account Number: ");
        String ac= sc.nextLine();
        System.out.println("Enter the Withdrawal Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        try {
            bankServices.withdraw(ac, amount);
            System.out.println("withdrawal of $" + amount + " successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ TRANSACTION FAILED: " + e.getMessage());
        }
    }

    private static void deposit(Scanner sc,BankServices bankServices) {
        System.out.println("Enter the Account Number: ");
        String ac= sc.nextLine();
        System.out.println("Enter the Amount to be deposited: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        try {
            bankServices.deposit(ac, amount);
            System.out.println("Deposit of $" + amount + " successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ TRANSACTION FAILED: " + e.getMessage());
        }
    }

    private static void openAccount(Scanner sc, BankServices bankServices) {
        System.out.println("Enter your name: ");
        String name= sc.nextLine();
        System.out.println("Enter your phone Number: ");
        long phoneNumber= sc.nextLong();
        System.out.println("Enter the initial deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter the type of account you want to open -Savings , Current");
        String type= sc.nextLine();
        bankServices.openAccount(name,amount,phoneNumber,type);
        System.out.println("your account has successfully created!");

    }

    private static void transfer(Scanner sc,BankServices bankServices) {
        System.out.println("Enter the Receiver's account number:");
        String rc=sc.nextLine();
        System.out.println("Enter the Sender's account number:");
        String sac= sc.nextLine();
        System.out.println("Enter the transfer amount: ");
        double amount= sc.nextDouble();
        sc.nextLine();

        try {
            bankServices.transfer(rc,sac, amount);
            System.out.println("transfer of $" + amount + " successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ TRANSACTION FAILED: " + e.getMessage());
        }
    }


}
