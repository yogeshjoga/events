package org.api.events.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.api.events.constents.VerficationState;
import org.api.events.dto.LoginDTO;
import org.api.events.dto.OTPdto;
import org.api.events.dto.RelativeResponceDto;
import org.api.events.dto.SignUpDTO;
import org.api.events.service.emailservice.IEmailService;
import org.api.events.service.userservice.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.api.events.constents.VerficationState.VERFICATION_COMPLETED;

@RestController
@RequestMapping("/api/auth/v2/")
@CrossOrigin({"${front_end_url}","${ngrok_url}"})
@Tag(name = "THIS IS THE NEW VERSION API VERSION 2 AUTH CONTROLLER", description = "This is for " +
        "User based auth controller not a relative based its updated version")
public class AuthControllerVer2 {


    private static final Logger log = LoggerFactory.getLogger(AuthControllerVer2.class);


    @Autowired
    private IEmailService emialService;

    @Autowired
    private IUserService userService;


    @PostMapping("/signup")
    @Operation(summary = "This is Post method for new User SignUp",
            tags = {"API Documentation", "Testing Completed"},
            description= "<h2> This is the SignUp api for new User creation </h2>" +
                    "<p>Require parameters <ul><li>userName</li>" +
                    "<li>password </li> </ul></p>")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO dto){
        RelativeResponceDto resp = userService.signUp(dto);
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
        return ResponseEntity.ok("Login successful");
    }

}
