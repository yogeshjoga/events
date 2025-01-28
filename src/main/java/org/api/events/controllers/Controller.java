package org.api.events.controllers;

import org.api.events.dto.Dto;
import org.api.events.models.Presentation;
import org.api.events.models.Relative;
import org.api.events.service.presentationservice.IPresentationService;
import org.api.events.service.relativeservice.IRelativeService;
import org.api.events.utils.IDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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

    @GetMapping("/getall")
    public ResponseEntity<List<Relative>> getAll() {
        return ResponseEntity.status(200).body(relativeService.getAllRelatives());
    }

    @GetMapping("/get")
    public ResponseEntity<String> testing(){

        log.info("\u001B[1;32m :: Testing relative :: \u001B[0m");
        return ResponseEntity.status(200).body("Hello this is for Testing the api application");
    }

    @PostMapping("presentation")
    public ResponseEntity<Dto> creatingRec(@RequestBody Dto dto){
        Relative relative = dtoMapper.dtoToRelative(dto);
        Presentation presentation = dtoMapper.dtoToPresentation(dto);
        // validate is pending
        relativeService.saveRelative(relative);
        // adding the relative
        presentation.setRelative(relative);
        presentationService.savePresentation(presentation);
        // pending
        return ResponseEntity.status(201).body(dto);
    }





}
