package com.example.vaccineManagementSystem.Repositories;
import com.example.vaccineManagementSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmailId(String emailID);
}
