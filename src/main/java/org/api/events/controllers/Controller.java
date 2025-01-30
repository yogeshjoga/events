package org.api.events.controllers;

import org.api.events.dto.Dto;
import org.api.events.dto.RespPresentationDto;
import org.api.events.models.Presentation;
import org.api.events.models.Relative;
import org.api.events.repo.RelativeRepo;
import org.api.events.service.presentationservice.IPresentationService;
import org.api.events.service.relativeservice.IRelativeService;
import org.api.events.utils.IDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private IRelativeService relativeService;

    @Autowired
    private IPresentationService presentationService;

    @Autowired
    private IDtoMapper dtoMapper;
    @Autowired
    private RelativeRepo relativeRepo;

    @GetMapping("/get")
    public ResponseEntity<String> testing(){
        return ResponseEntity.status(200).body("Hello this is for Testing the api application");
    }


    /**
     * <b>Get all relatives</b>
     * @return
     */
    @GetMapping("/getall")
    public ResponseEntity<List<Relative>> getAll() {
        return ResponseEntity.status(200).body(relativeService.getAllRelatives());
    }


    /**
     * <b>Presentations </b>
     * @param dto
     * @return
     */
    @PostMapping("presentation")
    public ResponseEntity<Dto> creatingRec(@RequestBody Dto dto){
        Relative relative = dtoMapper.dtoToRelative(dto);
        Optional<Relative> relative1 =  relativeService.getRelative(relative.getFirstName(), relative.getLastName(), relative.getCity());
        if(relative1.isEmpty()){
            log.info("\u001B[1;31m :: Testing relative :: \u001B[0m");
        }
        log.info("\u001B[1;31m :: Testing relative :: \u001B[0m");

        Presentation presentation = dtoMapper.dtoToPresentation(dto);
        // validate is pending
        relativeService.saveRelative(relative);
        // adding the relative
        presentation.setRelative(relative);
        presentationService.savePresentation(presentation);
        // pending
        return ResponseEntity.status(201).body(dto);
    }



    // -----------------------< Presentation api >--------------------------------------


    // get all
    // get join by relative_id
    @GetMapping("/allpre")
    public ResponseEntity<List<RespPresentationDto>> getAllPresentations() {
        List<Presentation> pres = presentationService.getAll();
        List<RespPresentationDto> resp = dtoMapper.presentationToRespDtoList(pres);
        return ResponseEntity.status(200).body(resp);
    }

    @GetMapping("/relatives1")
    public ResponseEntity<List<Relative>> getAllRelatives() {
        return null;
    }

    @GetMapping("/relatives2")
    public ResponseEntity<List<Relative>> getAllRelatives(@RequestParam(required = false) String city) {
        return null;
    }






}
