package lt.asprogramuoju.service.customerbill.ws.some.exception;

import lt.asprogramuoju.service.customerbill.core.exception.ErrorReasonEnum;
import lt.asprogramuoju.service.customerbill.core.exception.WSException;

@SuppressWarnings("java:S110")
public class SomeWsException extends WSException {

    public SomeWsException(Exception exception) {
        super(exception, ErrorReasonEnum.SOME_API_SERVICE_ERROR);
    }
}
