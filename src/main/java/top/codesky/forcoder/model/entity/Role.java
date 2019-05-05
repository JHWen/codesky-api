package top.codesky.forcoder.model.entity;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table role
 */
public class Role {
    /**
     * Database Column Remarks:
     *   自增id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   角色名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.role_name
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private String roleName;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Role() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.id
     *
     * @return the value of role.id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.id
     *
     * @param id the value for role.id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.role_name
     *
     * @return the value of role.role_name
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.role_name
     *
     * @param roleName the value for role.role_name
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}