package lt.asprogramuoju.service.customerbill.api.service;

import lombok.RequiredArgsConstructor;
import lt.asprogramuoju.gen.customerbill.model.CustomerBill;
import lt.asprogramuoju.service.customerbill.api.mapper.CustomerBillSomeWsMapper;
import lt.asprogramuoju.service.customerbill.core.util.DateUtil;
import lt.asprogramuoju.service.customerbill.ws.some.domain.InvoiceItem;
import lt.asprogramuoju.service.customerbill.ws.some.service.SomeWsRetriever;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final SomeWsRetriever someWsRetriever;
    private final CustomerBillSomeWsMapper customerBillSomeWsMapper;

    @Override
    public List<CustomerBill> getListCustomerInvoice(String accountId, Calendar invoiceDate) {
        //call SOME WS
        List<CustomerBill> someWsCustomerBills = collectFromSomeWs(accountId, invoiceDate);

        //call SOME Another WS
        List<CustomerBill> anotherWsCustomerBills = Collections.emptyList();

        //combine two lists
        Stream<CustomerBill> combinedStream = Stream.of(someWsCustomerBills, anotherWsCustomerBills).flatMap(Collection::stream);
        return combinedStream.collect(Collectors.toList());
    }

    private List<CustomerBill> collectFromSomeWs(String billAccountId, Calendar billDate) {
        List<InvoiceItem> invoiceHistory = someWsRetriever.getInvoiceList(
                SomeWsRetriever.InvoiceListJSONBody.builder()
                        .businessPartner(billAccountId)
                        .startDate(DateUtil.calendarToString(new SimpleDateFormat("yyyy-MM-dd"), billDate))
                        .build());

        return customerBillSomeWsMapper.mapToDomain(invoiceHistory);
    }
}
