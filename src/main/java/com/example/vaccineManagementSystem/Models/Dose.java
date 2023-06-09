package com.example.vaccineManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;


@Table(name = "dose")
@Entity
@Data
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String doseId;

    @CreationTimestamp
    private Date creationDate;
    @CreationTimestamp
    private Date vaccinationDate;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    private User user;



}
