package lt.asprogramuoju.service.customerbill.ws.some.http;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.List;

@Component
public class HttpManager {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HttpManager.class);

    /**
     * @param urlPath     URL to call POST.
     * @param urlParams   Any additional parameter, which will be added to url
     * @param headers     List of {@link Header} provided
     * @param jsonContent if provided then put to header Content-type not required
     * @return R
     */
    public JSONObject callPost(@NonNull URI urlPath,
                                        @Nullable List<NameValuePair> urlParams,
                                        List<Header> headers,
                                        @Nullable JSONObject jsonContent) throws IOException, URISyntaxException, HttpException {
        return callInternal(HttpMethod.POST, urlPath, urlParams, headers, jsonContent);
    }

    /**
     * @param urlPath     URL to call PUT.
     * @param urlParams   Any additional parameter, which will be added to url
     * @param headers     List of {@link Header} provided
     * @param jsonContent if provided then put to header Content-type not required
     * @return R
     */
    public JSONObject callPut(@NonNull URI urlPath,
                                       @Nullable List<NameValuePair> urlParams,
                                       List<Header> headers,
                                       @Nullable JSONObject jsonContent) throws IOException, URISyntaxException, HttpException {
        return callInternal(HttpMethod.PUT, urlPath, urlParams, headers, jsonContent);
    }

    public JSONObject callGet(URI urlPath, List<NameValuePair> urlParams, List<Header> headers) throws IOException, URISyntaxException {
        URI uri;
        if (urlParams == null || urlParams.isEmpty()) {
            uri = urlPath;
        } else {
            uri = new URIBuilder(urlPath).addParameters(urlParams).build();
        }

        LOGGER.debug("Executing uri {}", uri);

        HttpGet httpGet = new HttpGet(uri);
        if (headers != null) {
            headers.forEach(httpGet::addHeader);
        }
        addDefaultHeaderFor(httpGet);

        LOGGER.debug("Executing request {}", httpGet.getRequestLine());

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpResponse response = httpClient.execute(httpGet);
            AbstractMap.SimpleEntry<String, String> content = getContent(response);
            return null;
/*            if (content.getKey().startsWith("2")) {

            } else {
                LOGGER.error(content.getValue());

            }*/
        }
    }

    private JSONObject callInternal(HttpMethod httpMethod, @NonNull URI urlPath,
                              @Nullable List<NameValuePair> urlParams,
                              List<Header> headers,
                              @Nullable JSONObject jsonContent) throws URISyntaxException, IOException, HttpException {
        URI uri;
        if (urlParams == null || urlParams.isEmpty()) {
            uri = urlPath;
        } else {
            uri = new URIBuilder(urlPath).addParameters(urlParams).build();
        }

        HttpEntityEnclosingRequestBase http = null;
        if (httpMethod == HttpMethod.POST) {
            http = new HttpPost(uri);
        } else if (httpMethod == HttpMethod.PUT) {
            http = new HttpPut(uri);
        }

        if (headers != null && http != null) {
            headers.forEach(http::addHeader);
        }

        if (http != null) {
            addDefaultHeaderFor(http);
            LOGGER.debug("Executing request {}", http.getRequestLine());

            if (jsonContent != null) {
                LOGGER.debug("JSON body {}", jsonContent);
                StringEntity stringEntity = new StringEntity(jsonContent.toString());
                stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
                http.setEntity(stringEntity);
            }

            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpResponse response = httpClient.execute(http);
                AbstractMap.SimpleEntry<String, String> content = getContent(response);
                if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                    return this.toObject(content.getValue());
                } else {
                    LOGGER.error(content.getValue());
                    throw new HttpException(content.getValue());
                }
            }
        } else {
            throw new HttpException("System error! HttpMethod doesn't defined and is NULL");
        }
    }

    private JSONObject toObject(String jsonString) {
        return new JSONObject(jsonString);
    }

    private AbstractMap.SimpleEntry<String, String> getContent(HttpResponse response) throws IOException {
        String result = EntityUtils.toString(response.getEntity());
        LOGGER.debug("Response code {}: content:{}", response.getStatusLine().getStatusCode(), result);
        return new AbstractMap.SimpleEntry<>(Integer.toString(response.getStatusLine().getStatusCode()), result);
    }

    private void addDefaultHeaderFor(HttpRequestBase http) {
        http.addHeader(new BasicHeader("Accept", MediaType.APPLICATION_JSON_VALUE));
        http.addHeader(new BasicHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));
    }
}
