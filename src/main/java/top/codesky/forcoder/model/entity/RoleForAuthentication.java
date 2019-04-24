package top.codesky.forcoder.model.entity;

import org.springframework.security.core.GrantedAuthority;


public class RoleForAuthentication extends Role implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return super.getRoleName();
    }
}
