package com.capstone.service1.Services;

import com.capstone.service1.Models.PlanModel;
import com.capstone.service1.Repositary.PlanRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {
    @Autowired
    private PlanRepositary planRepository;

    // Method to find plans by category
    public List<PlanModel> getPlansByCategory(String category) {
        return planRepository.findByCategory(category);
    }

    // Method to retrieve all plans
    public List<PlanModel> getAllPlans() {
        return planRepository.findAll();
    }
    public Optional<PlanModel> getPlanById(int planId) {
        return Optional.ofNullable(planRepository.findById(planId).orElse(null));
    }
    public List<PlanModel> searchedPlans(String category, int price, int validity, String calls , String data){
        return planRepository.searchedPlan(category,price,validity,calls,data);
    }
}


