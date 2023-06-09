package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Models.Dose;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Repositories.DoseRepository;
import com.example.vaccineManagementSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseService {

    @Autowired
    private DoseRepository doseRepository;

    @Autowired
    private UserRepository userRepository;
    public String giveDose(String doesId, Integer userId) {
        User user=userRepository.findById(userId).get();
        Dose dose=new Dose();
        dose.setUser(user);
        dose.setDoseId(doesId);
        user.setDose(dose);
        userRepository.save(user);
        return "Dose succesfully vaccinated to the user "+ user.getName()+" and Dose id is this"+ doesId;
    }
}
