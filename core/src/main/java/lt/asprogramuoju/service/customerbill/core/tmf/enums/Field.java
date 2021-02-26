package lt.asprogramuoju.service.customerbill.core.tmf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Add here supported fields for validations
 *
 * @author Pavel Vrublevskij
 * @since 2020-02-03
 */
@AllArgsConstructor
@Getter
public enum Field {
    FIELD_BILL_ACCOUNT_ID("billingAccount.id", null),
    FIELD_BILL_DATE_GT("billDate.gt", new SimpleDateFormat("yyyy-MM-dd")),

    FIELD_DATE_PARAMETER("date", new SimpleDateFormat("yyyyMM"));

    private final String fieldName;
    private final DateFormat dateFormat;
}
