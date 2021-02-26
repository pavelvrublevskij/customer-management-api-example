package lt.asprogramuoju.service.customerbill.ws.some.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Calendar;

@Builder
@Getter
public class InvoiceItem {

    private final String invoice;
    private final String clientId;
    private final Calendar postingDate;
    private final Calendar creationDate;
    private final Status status;
    private final BigDecimal openAmount;
    private final BigDecimal totalAmount;
    private final String currency;
    private final String docType;
    private final Calendar dueDate;
    private final String correspondenceType;
    private final BigDecimal lastPeriodOverpaymentDebt;

    public enum Status {
        PAID, PART, NOT_PAID
    }
}
