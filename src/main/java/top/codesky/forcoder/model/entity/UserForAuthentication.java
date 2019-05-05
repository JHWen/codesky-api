package top.codesky.forcoder.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserForAuthentication extends UserAuthenticationInfo implements UserDetails {

    private List<RoleForAuthentication> authorities;


    public void setAuthorities(List<RoleForAuthentication> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String toString() {
        return "UserForAuthentication{" +
                "authorities=" + authorities +
                '}';
    }
}
