package com.learning.springboot;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "username", unique=true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "idcard", unique=true, nullable = false)
    private String idcard;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "address")
    private String address;

    public Integer getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIdcard(){
        return idcard;
    }

    public String getBirthday(){
        return birthday;
    }

    public String getAddress(){
        return address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
