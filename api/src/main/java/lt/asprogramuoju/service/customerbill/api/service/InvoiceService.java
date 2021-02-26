package lt.asprogramuoju.service.customerbill.api.service;

import lt.asprogramuoju.gen.customerbill.model.CustomerBill;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;

/**
 * @author Pavel Vrublevskij
 */
public interface InvoiceService {

    /**
     * Takes from two sources
     *
     * #accountId - Account id to get customer information and each invoices<br/>
     * #invoiceDate - date from when get invoices
     *
     * @param accountId BP Business Partner ID
     * @param invoiceDate Date from to get invoices.
     * @return list of {@link CustomerBill}
     */
    List<CustomerBill> getListCustomerInvoice(@NotNull String accountId, Calendar invoiceDate);

}
