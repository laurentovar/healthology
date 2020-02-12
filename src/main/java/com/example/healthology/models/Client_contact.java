package com.example.healthology.models;
import javax.persistence.*;

@Entity
@Table(name = "client_contact")
public class Client_contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false, length = 100)
    private String emergency_contact_name;

    @Column(nullable = false, length = 20)
    private String emergency_contact_number;

    @Column(nullable = false, length = 100)
    private String provider_name;

    @Column(nullable = false, length = 20)
    private String provider_contact_number;


    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client_id;

    public Client_contact(){}

    public Client_contact(String emergency_contact_name, String emergency_contact_number, String provider_name, String provider_contact_number, Client user) {
        this.emergency_contact_name = emergency_contact_name;
        this.emergency_contact_number = emergency_contact_number;
        this.provider_name = provider_name;
        this.provider_contact_number = provider_contact_number;
        this.client_id = client_id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmergency_contact_name() {
        return emergency_contact_name;
    }

    public void setEmergency_contact_name(String emergency_contact_name) {
        this.emergency_contact_name = emergency_contact_name;
    }

    public String getEmergency_contact_number() {
        return emergency_contact_number;
    }

    public void setEmergency_contact_number(String emergency_contact_number) {
        this.emergency_contact_number = emergency_contact_number;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getProvider_contact_number() {
        return provider_contact_number;
    }

    public void setProvider_contact_number(String provider_contact_number) {
        this.provider_contact_number = provider_contact_number;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }





}
