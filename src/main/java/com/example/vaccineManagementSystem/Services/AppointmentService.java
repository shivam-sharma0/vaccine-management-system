package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.DTO.AppointmentDto;
import com.example.vaccineManagementSystem.Exceptions.DoctorNotFound;
import com.example.vaccineManagementSystem.Exceptions.UserNotFound;
import com.example.vaccineManagementSystem.Models.Appointment;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Repositories.AppointmenRepository;
import com.example.vaccineManagementSystem.Repositories.DoctorRepository;
import com.example.vaccineManagementSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmenRepository appointmenRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;
    public String bookAppointment(AppointmentDto appointmentDto) throws DoctorNotFound ,UserNotFound{

        Optional<Doctor> optionalDoctor=doctorRepository.findById(appointmentDto.getDocId());
        if (optionalDoctor.isEmpty()){
            throw new DoctorNotFound("Doctor is not Available with this id");
        }

        Optional<User> optionalUser= userRepository.findById(appointmentDto.getUserId());
        if(optionalUser.isEmpty()){
            throw new UserNotFound("User is not present with this id");
        }

        Doctor doctor=optionalDoctor.get();
        User user=optionalUser.get();

        Appointment appointment=new Appointment();
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointment.setUser(user);
        appointment.setDoctor(doctor);

        appointment=appointmenRepository.save(appointment);

        doctor.getAppointmentList().add(appointment);
        user.getAppointmentList().add(appointment);

        userRepository.save(user);
        doctorRepository.save(doctor);
        String msgBody="Hii ! "+user.getName()+"\n Your appointment is succesfully Booked with "+doctor.getName()+"\n"
                +"at "+appointment.getAppointmentTime()+" on"+appointment.getAppointmentDate()+" You have to take mask on center"+
                "\n Vaccine Center address is  "+ doctor.getVaccinationCenter().getAddress();

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setSubject("Appointment Confirmation");
        mailMessage.setText(msgBody);
        mailMessage.setFrom("codershivam001@gmail.com");
        mailMessage.setTo(user.getEmailId());
        emailSender.send(mailMessage);

        return "Appointment is succesfully booked at "+appointmentDto.getAppointmentTime()+" on "+appointmentDto.getAppointmentDate();
    }
}
