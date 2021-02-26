package lt.asprogramuoju.service.customerbill.core.exception;

import lt.asprogramuoju.service.customerbill.core.interfaces.ExceptionFactory;

/**
 * Exception class represent bad input exceptions
 *
 * @author Pavel Vrublevskij
 */
public class BadInputException extends GeneralException {

    public BadInputException(ErrorReasonEnum error, Object... values) {
        super(error, values);
    }

    public static ExceptionFactory<BadInputException> factory() {
        return BadInputException::new;
    }
}
