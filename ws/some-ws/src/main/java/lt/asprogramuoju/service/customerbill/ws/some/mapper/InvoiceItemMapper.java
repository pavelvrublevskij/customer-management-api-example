package lt.asprogramuoju.service.customerbill.ws.some.mapper;

import lt.asprogramuoju.service.customerbill.core.util.DateUtil;
import lt.asprogramuoju.service.customerbill.ws.some.domain.InvoiceItem;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class InvoiceItemMapper {

    public InvoiceItem mapToInvoiceItem(JSONObject jsonObj) throws ParseException {
        String expectedPattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

        return InvoiceItem.builder()
                .invoice(jsonObj.getString("Invoice"))
                .clientId(jsonObj.getString("ClientId"))
                .postingDate(DateUtil.toCalendar(formatter, jsonObj.getString("PostingDate")))
                .creationDate(DateUtil.toCalendar(formatter, jsonObj.getString("CreationDate")))
                .status(jsonObj.getEnum(InvoiceItem.Status.class, "Status"))
                .openAmount(jsonObj.getBigDecimal("OpenAmount"))
                .totalAmount(jsonObj.getBigDecimal("TotalAmount"))
                .currency(jsonObj.getString("Currency"))
                .docType(jsonObj.getString("DocType"))
                .dueDate(DateUtil.toCalendar(formatter, jsonObj.getString("DueDate")))
                .correspondenceType(jsonObj.getString("CorrespondenceType"))
                .lastPeriodOverpaymentDebt(jsonObj.getBigDecimal("LastPeriodOverpaymentDebt"))
                .build();
    }
}
