package lt.asprogramuoju.service.customerbill.api.validator;

import lt.asprogramuoju.service.customerbill.api.controller.validator.CustomerBillFieldsValidator;
import lt.asprogramuoju.service.customerbill.core.exception.BadInputException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static lt.asprogramuoju.service.customerbill.core.tmf.enums.Field.FIELD_BILL_ACCOUNT_ID;
import static lt.asprogramuoju.service.customerbill.core.tmf.enums.Field.FIELD_BILL_DATE_GT;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerBillFieldsValidatorTest {

    private final CustomerBillFieldsValidator validatorClass = new CustomerBillFieldsValidator();

    @Test
    void validate_ShouldHasValidFields() {
        //when
        Map<String, String> resultMap = validatorClass.validate("billingAccount.id=1,billDate.gt=2020-01-15");

        //then
        assertTrue(resultMap.containsKey(FIELD_BILL_ACCOUNT_ID.getFieldName()));
        assertTrue(resultMap.containsKey(FIELD_BILL_DATE_GT.getFieldName()));
    }

    @Test
    void validate_OfInvalidFieldNames_ThrowException() {
        assertThrows(BadInputException.class, () -> validatorClass.validate("someName=1"));
        assertThrows(BadInputException.class, () -> validatorClass.validate("billingAccount.id=1,anotherName=2020-01-15"));
    }

    @Test
    void validate_OfValidFieldNames_DoesNotThrowException() {
        assertDoesNotThrow(() -> validatorClass.validate("billingAccount.id=1,billDate.gt=2020-01-15"));
    }
}
