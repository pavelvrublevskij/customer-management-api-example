package lt.asprogramuoju.service.customerbill.security.basic.config;

import lombok.Getter;
import lt.asprogramuoju.service.customerbill.security.basic.model.AppUser;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ConfigurationProperties("global")
@Getter
public class ApplicationsUsers {

    private final List<AppUser> users = new ArrayList<>();

    @Override
    public String toString() {
        Object[] usersStr = users.stream().map(AppUser::getUsername).toArray();
        return "ApplicationsUsers{users=" + Arrays.toString(usersStr) + "}";
    }
}
