package lt.asprogramuoju.service.customerbill.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lt.asprogramuoju.gen.customerbill.api.CustomerBillApi;
import lt.asprogramuoju.gen.customerbill.model.CustomerBill;
import lt.asprogramuoju.service.customerbill.api.controller.validator.CustomerBillFieldsValidator;
import lt.asprogramuoju.service.customerbill.api.service.InvoiceService;
import lt.asprogramuoju.service.customerbill.core.util.DateUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static lt.asprogramuoju.service.customerbill.core.tmf.enums.Field.FIELD_BILL_ACCOUNT_ID;
import static lt.asprogramuoju.service.customerbill.core.tmf.enums.Field.FIELD_BILL_DATE_GT;

@RestController
@RequiredArgsConstructor
public class CustomerBillController implements CustomerBillApi {

    private final InvoiceService invoiceService;
    private final CustomerBillFieldsValidator validator;

    @Override
    @SneakyThrows
    public ResponseEntity<List<CustomerBill>> listCustomerBill(String fields, Integer offset, Integer limit) {
        // do fields validation
        Map<String, String> mapFields = validator.validate(fields);

        // then
        return ResponseEntity.ok(
                invoiceService.getListCustomerInvoice(
                        mapFields.get(FIELD_BILL_ACCOUNT_ID.getFieldName()),
                        DateUtil.toCalendar(
                                FIELD_BILL_DATE_GT.getDateFormat(),
                                mapFields.get(FIELD_BILL_DATE_GT.getFieldName())
                        )
                ));
    }

}
