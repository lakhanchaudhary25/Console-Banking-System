package UnChecked_Exception;

public class FailedTransferException extends RuntimeException {
    public FailedTransferException(String message) {
        super(message);
    }
}
