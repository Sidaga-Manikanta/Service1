package com.capstone.service1.Repositary;

import com.capstone.service1.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepositary extends JpaRepository<UserModel, Integer> {

    // Custom query to find a user by their number
    @Query("SELECT u FROM UserModel u WHERE u.user_Number = :userNumber")
    Optional<UserModel> getByUserNumber(long userNumber);

}
