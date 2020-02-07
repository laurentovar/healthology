package com.example.healthology.models;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(columnDefinition = "TEXT")
	private String qualifications;

	@ManyToOne
	@JoinColumn (name = "person_id")
	private Person person;


	public Admin(String qualifications, Person person) {
		this.qualifications = qualifications;
		this.person = person;
	}

	public Admin () {};

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
