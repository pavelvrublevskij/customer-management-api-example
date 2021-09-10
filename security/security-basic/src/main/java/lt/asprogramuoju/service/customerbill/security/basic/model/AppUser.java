package lt.asprogramuoju.service.customerbill.security.basic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUser {
    private String username;
    private String password;
    private String[] roles;

}
