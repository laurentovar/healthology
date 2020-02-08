package com.example.healthology.models;
import javax.persistence.*;

@Entity
@Table(name = "resources")
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, length = 100)
    private String type_Resource;

    @ManyToOne
    @JoinColumn (name = "admin_id")
    private Admin admin;

    public Resources (){}

    public Resources(String category, String type_Resource, Admin admin) {
        this.category = category;
        this.type_Resource = type_Resource;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType_Resource() {
        return type_Resource;
    }

    public void setType_Resource(String type_Resource) {
        this.type_Resource = type_Resource;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
