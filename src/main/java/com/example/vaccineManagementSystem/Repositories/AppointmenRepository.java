package com.example.vaccineManagementSystem.Repositories;

import com.example.vaccineManagementSystem.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmenRepository extends JpaRepository<Appointment,Integer> {
}
