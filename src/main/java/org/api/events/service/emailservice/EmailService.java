package org.api.events.service.emailservice;


import jakarta.mail.internet.MimeMessage;
import org.api.events.constents.VerficationState;
import org.api.events.exceptions.EmailNotFoundException;
import org.api.events.exceptions.InvalidOTPException;
import org.api.events.models.OTP;
import org.api.events.models.Relative;
import org.api.events.repo.OTPRepo;
import org.api.events.repo.RelativeRepo;
import org.api.events.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements  IEmialService{


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
    public Boolean sendVerificationEmail(String email) {
        OTP newOtp = EmailUtils.generateOTP(email);
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
       OTP newOTP = otpRepo.findByRelativeEmail(email);
        Relative relative = relativeRepo.findByEmail(email);
       if (newOTP == null) {
           throw new EmailNotFoundException("Please check your email... or Verify the Email");
       }
       if (!newOTP.getOtp().equals(otp)) {
           throw new InvalidOTPException("Please Enter Valid OTP");
       }else{
           otpRepo.delete(newOTP);
           relative.setState(VerficationState.VERFICATION_COMPLETED);
           return relative.getState();
       }
    }



}
