package lt.asprogramuoju.service.customerbill.api.mapper;

import lt.asprogramuoju.gen.customerbill.model.CustomerBill;
import lt.asprogramuoju.service.customerbill.api.converter.ApiConverter;
import lt.asprogramuoju.service.customerbill.core.interfaces.Mapper;
import lt.asprogramuoju.service.customerbill.core.util.DateUtil;
import lt.asprogramuoju.service.customerbill.ws.some.domain.InvoiceItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerBillSomeWsMapper implements Mapper<List<InvoiceItem>, List<CustomerBill>> {

    public List<CustomerBill> mapToDomain(List<InvoiceItem> data) {
        return data.stream()
                .map(s -> CustomerBill.builder()
                        .amountDue(ApiConverter.toMoney(s.getOpenAmount(), s.getCurrency()))
                        .billDate(DateUtil.toOffsetDateTime(s.getCreationDate()))
                        .billNo(s.getInvoice())
                        .billingPeriod(ApiConverter.toTimePeriod(s.getCreationDate()))
                        .paymentDueDate(DateUtil.toOffsetDateTime(s.getDueDate()))
                        .taxIncludedAmount(ApiConverter.toMoney(s.getTotalAmount(), s.getCurrency()))
                        .build())
                .collect(Collectors.toList());
    }
}

