package com.example.healthology.models;
import javax.persistence.*;

@Entity
@Table(name= "group_client")
public class Group_client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group_id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client_id;

    public Group_client(Group group_id, Client client_id) {
        this.group_id = group_id;
        this.client_id = client_id;
    }
    public Group_client(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Group group_id) {
        this.group_id = group_id;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

}
