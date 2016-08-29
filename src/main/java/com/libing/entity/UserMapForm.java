package com.libing.entity;

import java.util.Map;

/**
 * Created by libing on 2016/8/29.
 */
public class UserMapForm {

    private Map<String, User> users;

    @Override
    public String toString() {
        return "UserMapForm{" +
                "users=" + users +
                '}';
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

}
