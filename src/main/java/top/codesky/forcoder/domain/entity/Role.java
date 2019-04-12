package top.codesky.forcoder.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    private int roleId;
    private String rolename;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String getAuthority() {
        return this.rolename;
    }
}
