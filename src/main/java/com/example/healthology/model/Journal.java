package com.example.healthology.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;
    @Column(nullable = false, length = 300)
    private String title;
    @Column(nullable = false, length = 10)
    private String rating;
    @Column(nullable = false, columnDefinition = "DATE")
//    YYYY-MM-DD
    private String date;
    @Column(nullable = false, columnDefinition = "BLOB")
    private String entry;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Journal() {}

    public Journal(String title, String rating, String date, String entry) {
        this.title = title;
        this.rating = rating = rating;
        this.date = date;
        this.entry = entry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}