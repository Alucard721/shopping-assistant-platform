package com.niit.interact.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    private int id;
    private String nickName;
    private String loginName;
    private String password;
    private String favCategory;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nick_name")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "fav_category")
    public String getFavCategory() {
        return favCategory;
    }

    public void setFavCategory(String favCategory) {
        this.favCategory = favCategory;
    }

    @Override
    public String toString() {
        return "users{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", favCategory='" + favCategory + '\'' +
                '}';
    }
}
