package com.example.healthology.models;
import javax.persistence.*;


@Entity
@Table(name= "user_history")
public class User_history {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "BLOB")
    private String description;

    @Column(nullable = false, columnDefinition = "BLOB")
    private String otherInformation;

    @Column(nullable = false, columnDefinition = "BLOB")
    private String previousCounseling;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User_history (){}

    public User_history(String description, String otherInformation, String previousCounseling, User user) {
        this.description = description;
        this.otherInformation = otherInformation;
        this.previousCounseling = previousCounseling;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public String getPreviousCounseling() {
        return previousCounseling;
    }

    public void setPreviousCounseling(String previousCounseling) {
        this.previousCounseling = previousCounseling;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
