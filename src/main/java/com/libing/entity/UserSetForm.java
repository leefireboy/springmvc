package com.libing.entity;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by libing on 2016/8/29.
 */
public class UserSetForm {

    private Set<User> users;

    public UserSetForm() {
        this.users = new LinkedHashSet<User>();
        users.add(new User());
        users.add(new User());
    }

    @Override
    public String toString() {
        return "UserSetForm{" +
                "users=" + users +
                '}';
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
