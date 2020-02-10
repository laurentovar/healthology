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
    private Groups group_id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client_id;


}
