package com.example.healthology.models;
import javax.persistence.*;

//import javax.persistence.Entity;
//import javax.persistence.Table;

@Entity
@Table(name= "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String group_name;

    @Column(nullable = false, length = 100)
    private String category_name;


    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;


    public Group(){}

    public Group(String group_name, String category_name) {
        this.group_name = group_name;
        this.category_name = category_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
