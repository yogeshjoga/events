package org.api.events.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.api.events.service.investmentservice.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"http://localhost:5173/","https://ef14-103-184-87-57.ngrok-free.app","http://localhost:5173/"})
@RequestMapping("/api/v1/investment/")
@Tag(name = "INVESTMENT CONTROLLER", description = "HELPING middle class people's financially")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;



    @GetMapping("/interest")
    public double getInvestmentService(@RequestParam(required = true, value = "INTEREST") double interest,
                                       @RequestParam(required = true,value = "YEARS") double years,
                                       @RequestParam(required = true,value = "AMOUNT") double amount) {
        return investmentService.getInterestRate(amount, interest, years);
    }



}
