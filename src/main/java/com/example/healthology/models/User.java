package com.example.healthology.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 11, columnDefinition = "int(11) unsigned")
    private Long id;
    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private boolean agreed_to_terms;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Journal> journals;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public User() {
    }

    public User(boolean agreed_to_terms) {
        this.agreed_to_terms = agreed_to_terms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAgreed_to_terms() {
        return agreed_to_terms;
    }

    public void setAgreed_to_terms(boolean agreed_to_terms) {
        this.agreed_to_terms = agreed_to_terms;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}