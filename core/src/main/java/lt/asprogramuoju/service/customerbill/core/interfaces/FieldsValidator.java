package lt.asprogramuoju.service.customerbill.core.interfaces;

import com.pivovarit.function.ThrowingBiConsumer;
import lt.asprogramuoju.service.customerbill.core.tmf.enums.Field;
import lt.asprogramuoju.service.customerbill.core.annotation.CanExtend;
import lt.asprogramuoju.service.customerbill.core.exception.BadInputException;
import lt.asprogramuoju.service.customerbill.core.exception.ErrorReasonEnum;
import lt.asprogramuoju.service.customerbill.core.exception.GeneralException;
import org.springframework.lang.NonNull;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is main validator which validate by TMF fields {@link Field}
 *
 * @author Pavel Vrublevskij
 * @see Field
 * @since 2020-02-03
 */
@CanExtend
public interface FieldsValidator {

    /**
     * @param fields String as comma separated value
     * @return Map of valid fields and each value
     * @throws BadInputException if one of fields are not valid or supported
     */
    default Map<String, String> validate(String fields) {
        String fieldsForValidate = "";
        if (fields == null) {
            throwIt(BadInputException.factory(), ErrorReasonEnum.FIELD_NOT_EMPTY);
        } else {
            fieldsForValidate = fields.replace(" ", "");
        }

        Map<String, String> resultMap = Arrays.stream(fieldsForValidate.split(","))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));

        resultMap.forEach(ThrowingBiConsumer.sneaky(this::validate));

        return resultMap;
    }

    /**
     * List of supported Fields, see {@link Field}.
     * <br/>Example:
     * <pre>
     * public List<Field> getValidFields() {
     *         return Arrays.asList(
     *                 FIELD_BILL_ACCOUNT_ID,
     *                 FIELD_BILL_DATE_GT);
     *     }
     * </pre>
     *
     * @return Collection of supported/valid fields
     */
    List<Field> getValidFields();

    default void validate(@NonNull String key, @NonNull String value) {
        validateFieldName(key);
        validateDateFieldValue(key, value);
    }

    default void validateFieldName(@NonNull String fieldName) {
        if (getValidFields().stream().noneMatch(field -> field.getFieldName().equals(fieldName))) {
            throwIt(BadInputException.factory(), ErrorReasonEnum.FIELD_NAME_INVALID_ERROR, fieldName);
        }
    }

    default void validateDateFieldValue(@NonNull String fieldName, @NonNull String fieldValue) {
        Optional<Field> field = getValidFields().stream()
                .filter(s -> s.getFieldName().equals(fieldName) && s.getDateFormat() != null)
                .findFirst();

        if (field.isPresent()) {
            try {
                field.get().getDateFormat().parse(fieldValue);
            } catch (ParseException e) {
                throwIt(BadInputException.factory(), ErrorReasonEnum.FIELD_DATE_VALUE_INVALID_ERROR, fieldName, fieldValue);
            }
        }
    }

    default <T extends GeneralException> void throwIt(ExceptionFactory<T> exceptionFactory,
                                                      ErrorReasonEnum messageEnum,
                                                      Object... messageValues) {
        throw exceptionFactory.create(messageEnum, messageValues);
    }
}
