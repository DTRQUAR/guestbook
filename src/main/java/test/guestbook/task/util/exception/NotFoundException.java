package test.guestbook.task.util.exception;

/**
 * Created by Qouer on 07.07.2016.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
