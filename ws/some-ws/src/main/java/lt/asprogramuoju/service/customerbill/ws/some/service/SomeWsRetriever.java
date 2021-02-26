package lt.asprogramuoju.service.customerbill.ws.some.service;

import lombok.Builder;
import lt.asprogramuoju.service.customerbill.ws.some.domain.InvoiceItem;
import org.json.JSONPropertyName;

import java.util.List;

/**
 * interface to get required data from SOME WS system<p/>
 *
 * @author Pavel Vrublevskij
 */
public interface SomeWsRetriever {

    String BASIC_AUTH = "Basic ";

    /**
     * TODO: note which outside procedure or db function is calling
     */
    String ENDPOINT_GET_INVOICE_LIST = "/GetInvoiceList";

    /**
     * Invoice list
     * <p/>
     * Example<br/>
     * Method send data by json format, see {@link InvoiceListJSONBody}:
     * <pre>
     * {
     *     "BusinessPartner": "1100000041",
     *     "StartDate": "20150901"
     * }
     * </pre>
     *
     * @param body - {@link InvoiceListJSONBody}
     * @return List of {@link InvoiceItem}
     */
    List<InvoiceItem> getInvoiceList(InvoiceListJSONBody body);

    /**
     * SOME WS supports types
     */
    enum RetrieveType {
        INVOICE
    }

    @Builder
    final class InvoiceListJSONBody {
        private final String businessPartner;
        private final String startDate;

        @JSONPropertyName("BusinessPartner")
        public String getBusinessPartner() {
            return businessPartner;
        }

        @JSONPropertyName("StartDate")
        public String getStartDate() {
            return startDate;
        }
    }
}
