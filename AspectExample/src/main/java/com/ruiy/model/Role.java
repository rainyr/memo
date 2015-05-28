package com.ruiy.model;

import java.util.*;

/**
 * Created by ruiyang on 15-05-21.
 */
public enum Role {
    ADMIN(new ArrayList<Permission>(){{
        add(Permission.EDIT);
        add(Permission.READ);
    }}),
    USER(new ArrayList<Permission>(){{
        add(Permission.READ);
    }});

    private ArrayList<Permission> role;

    Role(ArrayList<Permission> role) { this.role = role; }

    public ArrayList<Permission> getRole() {
        return role;
    }
}
