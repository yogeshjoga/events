package org.api.events.utils;

import org.api.events.models.OTP;
import org.api.events.service.emailservice.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;

@Component
public class EmailUtils {

    private static final Logger log = LoggerFactory.getLogger(EmailUtils.class);

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final int OTP_LENGTH = 6;


    public static OTP generateOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(SECURE_RANDOM.nextInt(10));
        }
        OTP otpObj = new OTP();
        otpObj.setOtp(otp.toString());
        return otpObj;
    }


    public static String generateEmailTemplete(String email, String otp) {

        log.info("\u001B[1;32m :: YOUR OTP IS : "+ otp +" :: \u001B[0m");

        return "<html>" +
                "<body>" +
                "<p>Dear Email Verification User,</p>" +
                "<p>Your Email verify Account (Email is: <b>" + email.substring(0, 4) + "*******@gmail.com</b>).</p>" +
                "<p>Your One Time PIN is: <b> <span style ='font-size: 32px;'>" + otp + "</span></b>, and it is valid for 10 minutes.</p>" +
                "<p><small>(Generated at " + new Date().toString() + ")</small></p>" +
                "<hr>" +
                "<p><i>This is an auto-generated email. Please do not reply to this email.</i></p>" +
                "</body>" +
                "</html>";
    }



}
