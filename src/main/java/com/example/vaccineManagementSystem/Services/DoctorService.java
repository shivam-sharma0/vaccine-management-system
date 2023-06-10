package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Exceptions.EmailIdIsEmpty;
import com.example.vaccineManagementSystem.Exceptions.ThisIdIsAlreadyRegister;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    public String addDoctor(Doctor doctor) throws EmailIdIsEmpty,ThisIdIsAlreadyRegister {
        if(doctor.getEmailId()==null || doctor.getEmailId().equals("")){
            throw new EmailIdIsEmpty("Email id is not present");
        }

        try{
            doctorRepository.save(doctor);
            return "Doctor is successfully Added ";
        }catch (Exception e){
            return new ThisIdIsAlreadyRegister("This email id is already regeisterd").getMessage();
        }
    }
}
