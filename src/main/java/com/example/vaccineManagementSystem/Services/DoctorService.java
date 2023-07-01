package com.example.vaccineManagementSystem.Services;

import com.example.vaccineManagementSystem.DTO.AssociateDoc;
import com.example.vaccineManagementSystem.Exceptions.CenterNotFound;
import com.example.vaccineManagementSystem.Exceptions.DoctorNotFound;
import com.example.vaccineManagementSystem.Exceptions.EmailIdIsEmpty;
import com.example.vaccineManagementSystem.Exceptions.ThisIdIsAlreadyRegister;
import com.example.vaccineManagementSystem.Models.Doctor;
import com.example.vaccineManagementSystem.Models.VaccinationCenter;
import com.example.vaccineManagementSystem.Repositories.DoctorRepository;
import com.example.vaccineManagementSystem.Repositories.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private VaccinationRepository vaccinationRepository;

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

    public String associateDoctor(AssociateDoc associateDoc) throws DoctorNotFound , CenterNotFound {
        Optional<Doctor> optionalDoctor=doctorRepository.findById(associateDoc.getDocId());
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFound("Sorry to say you Doctor is present with this Id");
        }


        Optional<VaccinationCenter> optionalVaccinationCenter=vaccinationRepository.findById(associateDoc.getCenterId());
       if (optionalVaccinationCenter.isEmpty()){
           throw new CenterNotFound("Sorry to say you but Center is not present with this id");
       }

       Doctor doctor=optionalDoctor.get();
       VaccinationCenter vaccinationCenter=optionalVaccinationCenter.get();

       doctor.setVaccinationCenter(vaccinationCenter);
       vaccinationCenter.getDoctorList().add(doctor);
       vaccinationRepository.save(vaccinationCenter);

       return "Doctor is with this id "+associateDoc.getDocId()+" is assoicate with this vaccination Center "+associateDoc.getCenterId()+vaccinationCenter.getCenterName();
    }
}
