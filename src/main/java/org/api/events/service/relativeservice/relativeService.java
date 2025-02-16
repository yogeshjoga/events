package org.api.events.service.relativeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.api.events.dto.*;
import org.api.events.exceptions.EmailAlreadyExisted;
import org.api.events.exceptions.EmailNotFoundException;
import org.api.events.models.Relative;
import org.api.events.repo.RelativeRepo;
import org.api.events.service.emailservice.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class relativeService implements IRelativeService {

    private static final Logger log = LoggerFactory.getLogger(relativeService.class);

    @Autowired
    private RelativeRepo relativeRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IEmailService emailService;





    @Override
    public List<Relative> getAllRelatives(UUID userId) {
        return relativeRepo.findAll();
    }

    @Override
    public Relative saveRelative(Relative relative) {
        return relativeRepo.save(relative);
    }

    @Override
    public Optional<Relative> getRelative(String firstName, String lastName, String city, UUID userId) {
        log.info("\u001B[1;32m :: Get relative By FirstName LastName City :: \u001B[0m");
       return relativeRepo.findRelativeByFirstNameAndLastNameAndCityAndUserId(firstName,lastName,city,userId);
    }

    @Override
    public Boolean isRelative(Relative relative, UUID userId) {
        Optional<Relative> rel = relativeRepo.findRelativeByFirstNameAndLastNameAndCityAndUserId(relative.getFirstName(),relative.getLastName(),relative.getCity(), userId);
        return rel.isPresent();
    }

    @Override
    public List<String> getAllUniqueCitys(UUID userId){
        return relativeRepo.findUniqueCitiesByCity(userId);
    }

    @Override
    public RelativeResponceDto signUp(SignUpDTO dto) {
        if(dto.getEmail() == null || dto.getEmail().isEmpty()){
            log.info("\u001B[1;31m :: EMAIL EMPTY EXCEPTION RAISED :: \u001B[0m");
            throw new EmailNotFoundException("Email is empty... Please enter a valid email");
        }
        Relative rel = objectMapper.convertValue(dto, Relative.class);
        if(relativeRepo.findByEmail(rel.getEmail()) != null){
            log.info("\u001B[1;31m :: EMAIL ALREADY EXISTED :: \u001B[0m");
            throw new EmailAlreadyExisted("Email is empty... Please enter a valid email");
        }
        String password = bCryptPasswordEncoder.encode(dto.getPassword());
        rel.setPassword(password);
        relativeRepo.save(rel);
        log.info("\u001B[1;32m :: OTP EMAIL SEND TO  "+ rel.getEmail() +"  :: \u001B[0m");
        emailService.sendVerificationEmail(rel.getEmail(),rel);
        return objectMapper.convertValue(rel, RelativeResponceDto.class);
    }




    // Testing method
    public List<AllCitysDto> getAllCitys(){
        UUID userId = UUID.randomUUID();
        return relativeRepo.findAllByCity(userId);
    }

    @Override
    public List<RelativeByCityPreDto> getRelativeByCityPresenations(String city, UUID userId){
        return relativeRepo.findByCityPresentations(city,userId);
    }
    @Override
    public List<RelativeByCityPreDto> getRelativeByCityReceiving(String city, UUID userId){
        return relativeRepo.findByCityReceiving(city, userId);
    }

    @Override
    public List<AmountFromRelatives> getAmountFromRelativesPresenations(UUID userId){
        return relativeRepo.findAmountFromRelativesPresentations(userId);
    }
    @Override
    public List<AmountFromRelatives> getAmountFromRelativesReceiving(UUID userId){
        return relativeRepo.findAmountFromRelativesReceiving(userId);
    }

    @Override
    public List<GiftsFromRelatives> getGiftsFromRelativesPresenations(UUID userId){
        return relativeRepo.findGiftsFromRelativesPresentations(userId);
    }
    @Override
    public List<GiftsFromRelatives> getGiftsFromRelativesReceiving(UUID userId){
        return relativeRepo.findGiftsFromRelativesReceiving(userId);
    }

    @Override
    public List<GoldFromRelatives> getGoldFromRelativesPresenations(UUID userId){
        return relativeRepo.findGoldFromRelativesPresentations(userId);
    }
    @Override
    public List<GoldFromRelatives> getGoldFromRelativesReceiving(UUID userId){
        return relativeRepo.findGoldFromRelativesReceiving(userId);
    }


    @Override
    public List<SilverFromRelatives> getSilverFromRelativesPresenations(UUID userId){
        return relativeRepo.findSilverFromRelativesPresentations(userId);
    }
    @Override
    public List<SilverFromRelatives> getSilverFromRelativesReceiving(UUID userId){
        return relativeRepo.findSilverFromRelativesReceiving(userId);
    }

}
