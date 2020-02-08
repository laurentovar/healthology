package com.example.healthology.models;
import javax.persistence.*;

@Entity
@Table(name= "groups_user")
public class Group_user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "groups_id")
    private Groups group_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;


}
