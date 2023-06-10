package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.DTO.UpdateEmail;
import com.example.vaccineManagementSystem.Exceptions.EmailIdIsEmpty;
import com.example.vaccineManagementSystem.Exceptions.ThereAreNoSuchUser;
import com.example.vaccineManagementSystem.Models.Dose;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(User user) {
        userRepository.save(user);
        return "User added succesfully";
    }

    public Date getVaccinationDate(Integer userId) {
        User user =userRepository.findById(userId).get();
        Dose dose=user.getDose();
        return dose.getVaccinationDate();
    }


    //Update Email id using DTO's Data transfer Objects
    public String updateEmail(UpdateEmail updatedEmail) throws EmailIdIsEmpty, ThereAreNoSuchUser {
        if(updatedEmail.getEmailId()==null){
            throw  new EmailIdIsEmpty("Email id is not Written");
        }
        try{
            User user=userRepository.findById(updatedEmail.getUserId()).get();
            user.setEmailId(updatedEmail.getEmailId());
            userRepository.save(user);
            return "Email id is updated succesfull";
        }catch (Exception ex) {
            throw new ThereAreNoSuchUser("There is no Such user with this email id ");
        }
    }


    public User getByEmailId(String emailId) {
        User user=userRepository.findByEmailId(emailId);
        return user;
    }
}
