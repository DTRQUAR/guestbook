package test.guestbook.task.util.exception;

/**
 * Created by Qouer on 07.07.2016.
 */
public class ExceptionUtil {

    public static void check(boolean found, int id) {
        check(found, "id=" + id);
    }

    public static <T> T check(T object, int id) {
        return check(object, "id=" + id);
    }

    public static <T> T check(T object, String msg) {
        check(object != null, msg);
        return object;
    }

    public static void check(boolean found, String msg) {
        if (!found) throw new NotFoundException("Not found entity with " + msg);
    }

}
