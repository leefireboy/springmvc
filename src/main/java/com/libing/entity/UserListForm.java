package com.libing.entity;

import java.util.List;

/**
 * Created by libing on 2016/8/26.
 */
public class UserListForm {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserListForm{" +
                "users=" + users +
                '}';
    }

}
