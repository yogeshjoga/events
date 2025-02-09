package org.api.events.service.relativeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.api.events.dto.RelativeResponceDto;
import org.api.events.dto.SignUpDTO;
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
        Relative rel = objectMapper.convertValue(dto, Relative.class);
        String password = bCryptPasswordEncoder.encode(dto.getPassword());
        rel.setPassword(password);
        relativeRepo.save(rel);
        log.info("\u001B[1;32m :: OTP EMAIL SEND TO  "+ rel.getEmail() +"  :: \u001B[0m");
        emailService.sendVerificationEmail(rel.getEmail());
        return objectMapper.convertValue(rel, RelativeResponceDto.class);
    }

}
