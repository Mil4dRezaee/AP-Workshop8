package exception;

public class DuplicateTitleException extends RuntimeException {

    public DuplicateTitleException(String msg) {
        super(msg);
    }

}