package lt.asprogramuoju.service.customerbill.ws.some.service;

import lombok.RequiredArgsConstructor;
import lt.asprogramuoju.service.customerbill.core.exception.WSException;
import lt.asprogramuoju.service.customerbill.ws.some.config.SomeWsPropertyHolder;
import lt.asprogramuoju.service.customerbill.ws.some.domain.InvoiceItem;
import lt.asprogramuoju.service.customerbill.ws.some.exception.SomeWsException;
import lt.asprogramuoju.service.customerbill.ws.some.http.HttpManager;
import lt.asprogramuoju.service.customerbill.ws.some.mapper.InvoiceItemMapper;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SomeWsRetrieverImpl implements SomeWsRetriever {

    protected static final Logger LOGGER = LoggerFactory.getLogger(SomeWsRetrieverImpl.class);

    private final SomeWsPropertyHolder propertyHolder;
    private final HttpManager httpManager;
    private final InvoiceItemMapper invoiceItemMapper;

    @Override
    @SuppressWarnings("java:S2647")
    public List<InvoiceItem> getInvoiceList(InvoiceListJSONBody body) {
        JSONObject jsonRequestBody = new JSONObject(body);

        List<Header> headers = new ArrayList<>();
        String encoding = Base64.getEncoder().encodeToString(
                (propertyHolder.getUsername() + ":" + propertyHolder.getPassword())
                        .getBytes(StandardCharsets.UTF_8));
        headers.add(new BasicHeader("Authorization", BASIC_AUTH + encoding));

        try {
            JSONObject result = httpManager.callPost(URI.create(propertyHolder.getUrl() + ENDPOINT_GET_INVOICE_LIST),
                    null, headers, jsonRequestBody);

            List<InvoiceItem> recList = new ArrayList<>();
            for (Object o : result.getJSONArray("Item")) {
                recList.add(invoiceItemMapper.mapToInvoiceItem((JSONObject) o));
            }

            return recList;
        } catch (IOException | URISyntaxException | HttpException | WSException | ParseException e) {
            throw new SomeWsException(e);
        }
    }

}
