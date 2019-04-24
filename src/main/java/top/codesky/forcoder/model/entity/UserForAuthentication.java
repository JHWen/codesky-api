package top.codesky.forcoder.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserForAuthentication extends User implements UserDetails {

    private List<RoleForAuthentication> roles;

    public List<RoleForAuthentication> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleForAuthentication> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

}
