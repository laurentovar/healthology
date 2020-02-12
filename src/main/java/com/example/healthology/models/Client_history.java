package com.example.healthology.models;
import javax.persistence.*;


@Entity
@Table(name= "client_history")
public class Client_history {

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
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client_id;


//    @OneToOne(mappedBy = "users") //, cascade = CascadeType.ALL)
//    private Client client;


    public Client_history(){}

    public Client_history(String description, String otherInformation, String previousCounseling, Client client_id) {
        this.description = description;
        this.otherInformation = otherInformation;
        this.previousCounseling = previousCounseling;
        this.client_id = client_id;
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

    public Client getClient() {
        return client_id;
    }

    public void setClient(Client client) {
        this.client_id = client;
    }

    public Client getClient_id() {
        return client_id;
    }
}
