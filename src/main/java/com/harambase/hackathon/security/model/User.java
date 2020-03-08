package com.harambase.hackathon.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harambase.hackathon.common.constant.RoleConst;
import com.harambase.hackathon.server.pojo.base.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails, Serializable {

    private String userId;

    private String username;

    private String password;

    private String type;

    private boolean enabled;

    private List<Authority> authorities;

    private String[] roles;

    public User(Person person) {

        this.password = person.getPassword();
        this.userId = person.getUserId();
        this.username = person.getUserId();//NOTICE HERE USE USERID AS USERNAME
        this.enabled = person.getStatus().equals("1");
        this.authorities = new ArrayList<>();
        this.roles = person.getRoleId().split("/");

        for (String role_id : roles) {
            if (StringUtils.isNotEmpty(role_id)) {
                Authority authority = new Authority();
                authority.setId(Long.parseLong(role_id));
                switch (role_id) {
                    case "0":
                        authority.setName(RoleConst.USER.getRoleName());
                        break;
                    case "1":
                        authority.setName(RoleConst.ADMIN.getRoleName());
                        break;
                    case "2":
                        authority.setName(RoleConst.TEACH.getRoleName());
                        break;
                    case "3":
                        authority.setName(RoleConst.LOGISTIC.getRoleName());
                        break;
                    case "4":
                        authority.setName(RoleConst.SYSTEM.getRoleName());
                        break;
                    case "5":
                        authority.setName(RoleConst.STUDENT.getRoleName());
                        break;
                    case "6":
                        authority.setName(RoleConst.FACULTY.getRoleName());
                        break;
                    case "7":
                        authority.setName(RoleConst.ADVISOR.getRoleName());
                        break;
                }
                authorities.add(authority);
            }
        }

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String[] getRoles() {
        return this.roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
