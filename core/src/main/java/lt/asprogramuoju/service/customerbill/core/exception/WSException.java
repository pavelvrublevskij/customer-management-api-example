package lt.asprogramuoju.service.customerbill.core.exception;

public class WSException extends GeneralException {

    public WSException(Exception exception, ErrorReasonEnum error, Object... values) {
        super(error, values);
        initCause(exception);
    }
}
