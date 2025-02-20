package org.api.events.service.emailservice;


import jakarta.mail.internet.MimeMessage;
import org.api.events.constents.VerficationState;
import org.api.events.exceptions.EmailNotFoundException;
import org.api.events.exceptions.InvalidOTPException;
import org.api.events.exceptions.OTPExpairedException;
import org.api.events.models.OTP;
import org.api.events.models.Relative;
import org.api.events.repo.OTPRepo;
import org.api.events.repo.RelativeRepo;
import org.api.events.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailService implements IEmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private OTPRepo otpRepo;

    @Value("${spring.mail.username}")
    private String from;


    private static final String subject = "Verification Email OTP FROM CHADIVIMPULU";
    @Autowired
    private RelativeRepo relativeRepo;


    @Override
    public Boolean sendVerificationEmail(String email, Relative relative) {
       // OTP dummyOTP = otpRepo.findByRelativeEmail(email);
        OTP dummyOTP= null;
        if(dummyOTP != null){
            log.info("\u001B[1;31m :: OTP ALREADY EXISTED, DELETED OLD OTP :: \u001B[0m");
            otpRepo.delete(dummyOTP);
        }

        OTP newOtp = EmailUtils.generateOTP();
        log.info("\u001B[1;32m :: YOUR OTP IS : "+ newOtp.getOtp() +" :: \u001B[0m");
        newOtp.setRelative(relative);
        log.info("\u001B[1;32m :: CREATED NEW OTP SUCCESSFULLY :: \u001B[0m");
        otpRepo.save(newOtp);
        String body = EmailUtils.generateEmailTemplete(email, newOtp.getOtp());
        return sendEmail(email, subject, body);
    }


    public Boolean sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setFrom(from);
            helper.setCc(from);
            helper.setBcc(from);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    // ------------------------------< VALIDATION OTP >----------------------------------


    @Override
    public VerficationState verifyOTP(String email, String otp) {

        //OTP newOTP = otpRepo.findByRelativeEmail(email);
        OTP newOTP= null;
       Relative relative = null; //relativeRepo.findByEmail(email);
       if (newOTP == null) {
           throw new EmailNotFoundException("Please check your email... or Verify the Email");
       }
       if(isOTPExpired(newOTP.getCreated())){
           log.info("\u001B[1;31m :: OTP EXPAIRED :: \u001B[0m");
           throw new OTPExpairedException("Your OTP was expired please try again");
       }
       if (!newOTP.getOtp().equals(otp)) {
           throw new InvalidOTPException("Please Enter Valid OTP");
       }else{
           otpRepo.delete(newOTP);
          relative.setState(VerficationState.VERFICATION_COMPLETED);
           // Updating the relative verification status
           Relative respRel =null; //relativeRepo.findByEmail(email);
           respRel.setState(VerficationState.VERFICATION_COMPLETED);
           relativeRepo.save(respRel);
           //relativeRepo.save(relative);
           return relative.getState();
       }

    }

    public boolean isOTPExpired(LocalDateTime createdTime) {
        LocalDateTime expiryTime = createdTime.plusMinutes(10);
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.isAfter(expiryTime);
    }

}
