package org.api.events.service.emailservice;

import org.api.events.models.OTP;
import org.api.events.models.User;
import org.api.events.repo.OTPRepo;
import org.api.events.repo.UserRepo;
import org.api.events.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

@Service
public class EmailService2 implements FIEmailService2{



    private static final Logger log = LoggerFactory.getLogger(EmailService2.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OTPRepo otpRepo;

    @Autowired
    private IEmailService emailService;


    @Value("${spring.mail.username}")
    private String from;


    private static final String subject = "Verification Email OTP FROM CHADIVIMPULU";



    @Override
    public Boolean sendVerificationEmail(String email, User user) {

        OTP dummyOTP = otpRepo.findByUserEmail(email);

        if(dummyOTP != null){
            log.info("\u001B[1;31m :: OTP ALREADY EXISTED, DELETED OLD OTP V2 :: \u001B[0m");
            otpRepo.delete(dummyOTP);
        }

        OTP newOtp = EmailUtils.generateOTP();
        log.info("\u001B[1;32m :: YOUR OTP IS V2 : "+ newOtp.getOtp() +" :: \u001B[0m");
        newOtp.setUser(user);
        log.info("\u001B[1;32m :: CREATED NEW OTP SUCCESSFULLY V2 :: \u001B[0m");
        otpRepo.save(newOtp);
        String body = EmailUtils.generateEmailTemplete(email, newOtp.getOtp());
        return emailService.sendEmail(email, subject, body);

    }

}
