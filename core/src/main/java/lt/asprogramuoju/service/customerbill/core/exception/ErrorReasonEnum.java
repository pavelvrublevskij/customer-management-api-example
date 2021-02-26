package lt.asprogramuoju.service.customerbill.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Error messages storage
 *
 * @author Pavel Vrublevskij
 */
@Getter
@RequiredArgsConstructor
public enum ErrorReasonEnum {
    // 10XX errorCodes used for internal validations and errors
    FIELD_NOT_EMPTY(1000, "Field couldn't be empty"),
    FIELD_NAME_INVALID_ERROR(1001, "Field \"%s\" doesn't support or invalid"),
    FIELD_DATE_VALUE_INVALID_ERROR(1002, "Field \"%s\" has bad date \"%s\" format. Date format should be YYYY-MM-DD"),

    // 11XX rules errors
    RULE_IDTYPE_SOUCE_ERROR(1100, "idType \"%s\" should have one of source values and could not be empty"),
    RULE_IDTYPE_ERROR(1101, "idType \"%s\" not implemented. Please use supported types"),

    // SOME API error messages
    SOME_API_SERVICE_ERROR(4000, "SOME API Server Error");

    private final int errorCode;
    private final String errorMessage;

    public String getErrorCode() {
        return "" + this.errorCode;
    }
}

