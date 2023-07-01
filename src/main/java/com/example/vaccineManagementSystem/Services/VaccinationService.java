package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.Exceptions.VaccinationAddressNotFound;
import com.example.vaccineManagementSystem.Models.VaccinationCenter;
import com.example.vaccineManagementSystem.Repositories.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService {

    @Autowired
    private VaccinationRepository vaccinationRepository;
    public String addCenter(VaccinationCenter vaccinationCenter) throws VaccinationAddressNotFound {
        if (vaccinationCenter.getAddress().equals(null)){
            throw  new VaccinationAddressNotFound("This address is not found or it is a valid address");
        }

        vaccinationRepository.save(vaccinationCenter);
        return "VaccinationCenter is added succesfully";
    }

}
