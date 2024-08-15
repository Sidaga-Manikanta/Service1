package com.capstone.service1.Repositary;


import com.capstone.service1.Models.PlanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepositary extends JpaRepository<PlanModel, Integer> {
    @Query("SELECT p FROM PlanModel p WHERE p.plan_Category = :category")
    List<PlanModel> findByCategory(@Param("category") String category);

    @Query("SELECT p FROM PlanModel p WHERE p.plan_Id = :planId")
    PlanModel findById(@Param("planId") String planId);


    @Query("select p from PlanModel p where " +
            "(:searchedPrice is null or p.plan_Price = :searchedPrice) or " +
            "(:searchedCategory is null or p.plan_Category like %:searchedCategory%) or " +
            "(:searchedValidity is null or p.plan_Validity = :searchedValidity) or " +
            "(:searchCalls is null or p.plan_Voice_Calls like %:searchCalls%) or " +
            "(:searchedData is null or p.plan_Data like %:searchedData%)")
    List<PlanModel> searchedPlan(@Param("searchedCategory") String category, @Param("searchedPrice") int price, @Param("searchedValidity") int validity, @Param("searchCalls") String calls, @Param("searchedData") String data);
}


