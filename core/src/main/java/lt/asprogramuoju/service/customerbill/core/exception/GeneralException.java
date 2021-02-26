package lt.asprogramuoju.service.customerbill.core.exception;

import lombok.Getter;
import lt.asprogramuoju.service.customerbill.core.annotation.CanExtend;
import lt.asprogramuoju.service.customerbill.core.interfaces.ExceptionFactory;
import lt.asprogramuoju.service.customerbill.core.interfaces.FieldsValidator;

/**
 * Class used for exceptions by {@link ErrorReasonEnum} rules.<br />
 * Main part for this class is {@link ExceptionFactory} functional interface.
 * Usages:
 *  - For validation, see {@link FieldsValidator}
 *
 * <p/>
 * This class is abstract to force the programmer to extend the class.<br />
 *
 * Extended class example:<br/>
 * <b><pre>
 *     public class MyException extends GeneralException {
 *
 *     public MyException(ErrorMessagesEnum error, Object... values) {
 *         super(error, values);
 *     }
 *
 *     public static ExceptionFactory<MyException> factory() {
 *         return MyException::new;
 *     }
 * }
 * </pre></b>
 *
 * @author Pavel Vrublevskij
 * @see ErrorReasonEnum
 * @see ExceptionFactory
 * @see FieldsValidator
 * @since 2021-02-03
 */
@CanExtend
@Getter
public abstract class GeneralException extends RuntimeException {

    private final String errorCode;
    private final ErrorReasonEnum anEnum;
    private final transient Object[] errorReasonValues;

    protected GeneralException(ErrorReasonEnum error, Object... values) {
        this.anEnum = error;
        this.errorCode = error.getErrorCode();
        this.errorReasonValues = values;
    }

    public String getReason() {
        try {
            return String.format(anEnum.getErrorMessage(), errorReasonValues);
        } catch(RuntimeException e) {
            throw new RuntimeException("String.format error for text: " + anEnum.getErrorMessage(), e);
        }
    }
}
