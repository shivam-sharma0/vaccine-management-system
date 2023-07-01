package com.example.vaccineManagementSystem.Controllers;

import com.example.vaccineManagementSystem.DTO.UpdateEmail;
import com.example.vaccineManagementSystem.Exceptions.EmailIdIsEmpty;
import com.example.vaccineManagementSystem.Exceptions.ThereAreNoSuchUser;
import com.example.vaccineManagementSystem.Models.User;
import com.example.vaccineManagementSystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping ("/getVaccinationDate")
    public Date getVaccinationDate(@RequestParam Integer userId){
        return userService.getVaccinationDate(userId);
    }

    @PutMapping("/updateEmail")
    public String updateEmail(@RequestBody UpdateEmail updatedEmail){
        try{
            return   userService.updateEmail(updatedEmail);
        } catch (ThereAreNoSuchUser e) {
            return e.getMessage();
        } catch (EmailIdIsEmpty e) {
            return e.getMessage();
        }
    }

    @GetMapping("/getUserByEmailId/{emailId}")
    public User getByEmailId(@PathVariable String emailId){

        return userService.getByEmailId(emailId);
    }

}
