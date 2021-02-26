package lt.asprogramuoju.service.customerbill.ws.some.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class SomeWsPropertyHolder {

    @Value("${ws.some.username}")
    private String username;

    @Value("${ws.some.pwd}")
    private String password;

    @Value("${ws.some.endpointURL}")
    private String url;
}
