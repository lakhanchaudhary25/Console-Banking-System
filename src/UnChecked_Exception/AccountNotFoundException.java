package UnChecked_Exception;

public class AccountNotFoundException extends  RuntimeException{
    public  AccountNotFoundException(String message){
        super(message);
    }
}
