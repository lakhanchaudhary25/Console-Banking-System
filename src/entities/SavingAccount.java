package entities;

public class SavingAccount extends BankAccount{
    private long phoneNumber;

    public SavingAccount(String customerName, double initialDeposit, long phoneNumber, String type) {
        super( initialDeposit, type,customerName);


        this.phoneNumber=phoneNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }


}
