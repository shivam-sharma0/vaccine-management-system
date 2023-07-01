package com.example.vaccineManagementSystem.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class AppointmentDto {

    private Integer docId;
    private Integer userId;

    private Date appointmentDate;

    private String appointmentTime;
}
