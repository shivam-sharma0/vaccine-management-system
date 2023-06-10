package com.example.vaccineManagementSystem.Models;

import Enums.Gender;
import jakarta.persistence.*;

@Table(name = "doctors")
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docId;

    private String name;

    private int age;

    @Enumerated(value = EnumType.STRING)
    private Enums.Gender gender;

    public Doctor(Integer docId, String name, int age, Gender gender, String emailId) {
        this.docId = docId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.emailId = emailId;
    }

    @Column(unique = true)
    private String emailId;

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
