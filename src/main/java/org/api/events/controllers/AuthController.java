package org.api.events.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.api.events.config.UtilConfig;
import org.api.events.constents.VerficationState;
import org.api.events.dto.OTPdto;
import org.api.events.dto.RelativeResponceDto;
import org.api.events.dto.SignUpDTO;
import org.api.events.service.emailservice.IEmailService;
import org.api.events.service.relativeservice.IRelativeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.api.events.constents.VerficationState.VERFICATION_COMPLETED;

@RestController
@RequestMapping("/public")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private IEmailService emialService;
    @Autowired
    private IRelativeService relativeService;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;



    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO dto){
        RelativeResponceDto resp = relativeService.signUp(dto);
        log.info("\u001B[1;32m :: SIGNUP SUCCESSFULL :: \u001B[0m");
        return ResponseEntity.status(200).body(resp);
    }

    @PostMapping("/otp")
    public ResponseEntity<?> otpValidation(@RequestBody OTPdto dto){
        VerficationState state = emialService.verifyOTP(dto.getEmail(), dto.getOtp());
        if(state.equals(VERFICATION_COMPLETED)){
            return ResponseEntity.ok("OTP verified");
        }
        return ResponseEntity.status(404).body("Wrong OTP please try again");
    }





}
