package com.mumu.day02.utils;

/**
 * User
 *
 * @author liuzhen
 * @version 1.0.0 2021/2/19 10:20
 */
public class User {

    private String userName;
    private String password;
    private Integer idCard;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "User{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + ", idCard=" + idCard + '}';
    }
}
