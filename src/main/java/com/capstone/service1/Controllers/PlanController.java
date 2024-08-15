package com.capstone.service1.Controllers;

import com.capstone.service1.Models.PlanModel;
import com.capstone.service1.Models.ValidRecharges;
import com.capstone.service1.Repositary.ValidRechargesRepo;
import com.capstone.service1.Services.PlanService;
import com.capstone.service1.Services.RechargeSuccessEmail;
import com.capstone.service1.Services.UserService;
import com.capstone.service1.Services.ValidRechargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    UserService userService;

    @Autowired
    private ValidRechargesRepo validRecharges;
    @Autowired
    private ValidRechargesService validRechargesService;

    @Autowired
    RechargeSuccessEmail rechargeSuccessEmail;

    @Autowired
    private UserService user_Service;

    @GetMapping("/category/{category}")
    public List<PlanModel> getPlansByCategory(@PathVariable String category) {
        return planService.getPlansByCategory(category);
    }

    @GetMapping("/")
    public List<PlanModel> getAllPlans() {
        return planService.getAllPlans();
    }
    @GetMapping("/plan/{planId}")
    public PlanModel getPlanById(@PathVariable int planId) {
        Optional<PlanModel> plan =planService.getPlanById(planId);
        return plan.orElse(null);
    }

    @PostMapping("/payment/sucess")
    public boolean paymentSucess(@RequestBody Map<String,Object> data) {

        int planId = (int)data.get("planId");
        long mobileNumber = (long)data.get("mobileNumber");

        //getting email of the user
        String user_Email=userService.getEmailByNumber(mobileNumber);
        String email_Subject="SucessFull Recharge from LetsConnect";
        String email_message="Hi Greetings from LetsConnect ,you sucessfully recharged Hope you enjoy the service, Thanks";

        ValidRecharges vr=validRechargesService.addingRecharge(planId,mobileNumber,"upi");
        System.out.println(vr);
        rechargeSuccessEmail.sendSimpleEmail(user_Email,email_Subject,email_message);
        return true;
    }

    @GetMapping("/search/{searchedQuery}")
    public List<PlanModel>searchedPlans( @PathVariable String  searchedQuery) {

        String searchKeyword = searchedQuery;
        System.out.println(searchKeyword);

        if (searchKeyword.matches(".*[a-zA-Z]+.*")){
            return planService.searchedPlans(searchKeyword,0,0,searchKeyword,searchKeyword);
        }
        else{
            int price, calls=0;
            calls =Integer.parseInt(searchKeyword);
            price=Integer.parseInt(searchKeyword);

            return planService.searchedPlans(searchKeyword,price,calls,searchKeyword,searchKeyword);
        }
    }
    @GetMapping("/userActivePlans")
    public List<ValidRecharges> getUserActivePlans(@RequestParam("userNumber") long userNumber) {
//        long uNum=Long.parseLong(userNumber);
        System.out.println(userNumber);
        return validRechargesService.getActivePlans(userNumber);
    }




}
