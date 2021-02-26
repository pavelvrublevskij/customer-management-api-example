package lt.asprogramuoju.service.customerbill.core.interfaces;

import lt.asprogramuoju.service.customerbill.core.exception.ErrorReasonEnum;
import lt.asprogramuoju.service.customerbill.core.exception.GeneralException;

/**
 * Implementation that only creates exceptions constructed with a {@link ErrorReasonEnum} and each arguments
 *
 * @param <T> Exception class
 * @author Pavel Vrublevskij
 */
@FunctionalInterface
public interface ExceptionFactory<T extends GeneralException> {

    T create(ErrorReasonEnum messageEnum, Object... messageValues);
}
