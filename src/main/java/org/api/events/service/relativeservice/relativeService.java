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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public List<Relative> getAllRelatives() {
        return relativeRepo.findAll();
    }

    @Override
    public Relative saveRelative(Relative relative) {
        return relativeRepo.save(relative);
    }

    @Override
    public Optional<Relative> getRelative(String firstName, String lastName, String city) {
        log.info("\u001B[1;32m :: Get relative By FirstName LastName City :: \u001B[0m");
       return relativeRepo.findRelativeByFirstNameAndLastNameAndCity(firstName,lastName,city);
    }

    @Override
    public Boolean isRelative(Relative relative) {
        Optional<Relative> rel = relativeRepo.findRelativeByFirstNameAndLastNameAndCity(relative.getFirstName(),relative.getLastName(),relative.getCity());
        return rel.isPresent();
    }

    @Override
    public List<String> getAllUniqueCitys(){
        return relativeRepo.findUniqueCitiesByCity();
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
        return relativeRepo.findAllByCity();
    }

    @Override
    public List<RelativeByCityPreDto> getRelativeByCityPresenations(String city){
        return relativeRepo.findByCityPresentations(city);
    }
    @Override
    public List<RelativeByCityPreDto> getRelativeByCityReceiving(String city){
        return relativeRepo.findByCityReceiving(city);
    }

    @Override
    public List<AmountFromRelatives> getAmountFromRelativesPresenations(){
        return relativeRepo.findAmountFromRelativesPresentations();
    }
    @Override
    public List<AmountFromRelatives> getAmountFromRelativesReceiving(){
        return relativeRepo.findAmountFromRelativesReceiving();
    }

    @Override
    public List<GiftsFromRelatives> getGiftsFromRelativesPresenations(){
        return relativeRepo.findGiftsFromRelativesPresentations();
    }
    @Override
    public List<GiftsFromRelatives> getGiftsFromRelativesReceiving(){
        return relativeRepo.findGiftsFromRelativesReceiving();
    }

    @Override
    public List<GoldFromRelatives> getGoldFromRelativesPresenations(){
        return relativeRepo.findGoldFromRelativesPresentations();
    }
    @Override
    public List<GoldFromRelatives> getGoldFromRelativesReceiving(){
        return relativeRepo.findGoldFromRelativesReceiving();
    }


    @Override
    public List<SilverFromRelatives> getSilverFromRelativesPresenations(){
        return relativeRepo.findSilverFromRelativesPresentations();
    }
    @Override
    public List<SilverFromRelatives> getSilverFromRelativesReceiving(){
        return relativeRepo.findSilverFromRelativesReceiving();
    }

}
