package lt.asprogramuoju.service.customerbill.api.controller.validator;

import lt.asprogramuoju.service.customerbill.core.interfaces.FieldsValidator;
import lt.asprogramuoju.service.customerbill.core.tmf.enums.Field;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static lt.asprogramuoju.service.customerbill.core.tmf.enums.Field.FIELD_BILL_ACCOUNT_ID;
import static lt.asprogramuoju.service.customerbill.core.tmf.enums.Field.FIELD_BILL_DATE_GT;
import static lt.asprogramuoju.service.customerbill.core.tmf.enums.Field.FIELD_DATE_PARAMETER;

/**
 * Customer Bills filter fields validator
 *
 * @author Pavel Vrublevskij
 */
@Component
@Qualifier("customerBillValidator")
public class CustomerBillFieldsValidator implements FieldsValidator {

    public List<Field> getValidFields() {
        return Arrays.asList(
                FIELD_BILL_ACCOUNT_ID,
                FIELD_BILL_DATE_GT,
                FIELD_DATE_PARAMETER);
    }
}
