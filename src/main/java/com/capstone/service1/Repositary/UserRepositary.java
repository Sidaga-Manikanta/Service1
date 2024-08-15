package com.capstone.service1.Repositary;

import com.capstone.service1.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositary extends JpaRepository<UserModel, Integer> {
}
