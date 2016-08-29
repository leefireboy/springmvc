package com.libing.entity;

/**
 * Created by libing on 2016/8/26.
 */
public class User {

    private String name;
    private Integer age;
    private ContactInfo contactInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return age != null ? age.equals(user.age) : user.age == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", contactInfo=" + contactInfo +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    /*public static void main(String[] args) {
        Set<User> users = new HashSet<User>();
        User u1 = new User();
        u1.setName("aa");
        User u2 = new User();
        u2.setName("aa");
        users.add(u1);
        users.add(u2);
        System.out.println("size:" + users.size());
        System.out.println(users);
    }*/

}
