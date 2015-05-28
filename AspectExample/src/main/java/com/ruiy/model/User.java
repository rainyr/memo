package com.ruiy.model;

/**
 * Created by ruiyang on 15-05-21.
 */
public class User {
    private String id;
    private String name;
    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
