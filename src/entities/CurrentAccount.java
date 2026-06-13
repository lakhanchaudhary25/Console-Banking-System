package entities;

public class CurrentAccount extends BankAccount{

    private long phoneNumber;
    public CurrentAccount( String customerName, double initialDeposit, long phoneNumber, String type) {
        super( initialDeposit, type,customerName);

        this.phoneNumber=phoneNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

}
