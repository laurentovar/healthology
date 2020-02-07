package com.example.healthology.models;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;
    @Column(nullable = false, length = 100)
    private String fist_name;
    @Column(nullable = false, length = 100)
    private String last_name;
    @Column(nullable = false, length = 200)
    private String profile_img;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 20, unique = true)
    private String phone_number;
    @Column(nullable = false, length = 200, unique = true)
    private String email;
    @Column(nullable = false, columnDefinition = "BLOB")
    private String about_me;

    public Person() {}

    public Person(String first_name, String last_name, String profile_img, String password, String phone_number, String email, String about_me) {
        this.fist_name = first_name;
        this.last_name = last_name;
        this.profile_img = profile_img;
        this.password = password;
        this.phone_number = phone_number;
        this.email = email;
        this.about_me = about_me;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFist_name() {
        return fist_name;
    }

    public void setFist_name(String fist_name) {
        this.fist_name = fist_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }
}

