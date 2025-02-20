package org.api.events.service.userservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.api.events.dto.RelativeResponceDto;
import org.api.events.dto.SignUpDTO;
import org.api.events.exceptions.EmailAlreadyExisted;
import org.api.events.exceptions.EmailNotFoundException;
import org.api.events.exceptions.UserNotFoundException;
import org.api.events.models.User;
import org.api.events.repo.UserRepo;
import org.api.events.service.emailservice.FIEmailService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private FIEmailService2 emailService;




    @Override
    public RelativeResponceDto signUp(SignUpDTO dto) {
        if(dto.getEmail() == null || dto.getEmail().isEmpty()){
            log.info("\u001B[1;31m :: EMAIL EMPTY EXCEPTION RAISED :: \u001B[0m");
            throw new EmailNotFoundException("Email is empty... Please enter a valid email");
        }
        User user = objectMapper.convertValue(dto, User.class);
        if(userRepo.findByEmail(user.getEmail()).isPresent()){
            log.info("\u001B[1;31m :: EMAIL ALREADY EXISTED :: \u001B[0m");
            throw new EmailAlreadyExisted("Email is empty... Please enter a valid email");
        }
        String password = bCryptPasswordEncoder.encode(dto.getPassword());
        user.setPassword(password);
        userRepo.save(user);
        log.info("\u001B[1;32m :: OTP EMAIL SEND TO  "+ user.getEmail() +"  :: \u001B[0m");
        emailService.sendVerificationEmail(user.getEmail(),user);
        return objectMapper.convertValue(user, RelativeResponceDto.class);
    }

    @Override
    public User getUserByEmail(String email) {
        if(email == null || email.isEmpty()){
            throw new EmailNotFoundException("Email is empty... Please enter a valid email");
        }
        if(userRepo.findByEmail(email).isPresent()){
            return userRepo.findByEmail(email).get();
        }
        throw new UserNotFoundException("user not found from this email id please check your email id or re-enter");
    }


    @Override
    public User getUserById(UUID userId){
        return userRepo.findById(userId).get();
    }
}
