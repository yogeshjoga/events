package org.api.events.controllers;

import org.api.events.dto.Dto;
import org.api.events.models.Presentation;
import org.api.events.models.Relative;
import org.api.events.service.presentationservice.IPresentationService;
import org.api.events.service.relativeservice.IRelativeService;
import org.api.events.utils.DtoMapper;
import org.api.events.utils.RelativeUtils;
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
    private DtoMapper dtoMapper;

    @GetMapping("/getall")
    public ResponseEntity<List<Relative>> getAll() {
        return ResponseEntity.status(200).body(relativeService.getAllRelatives());
    }

    @GetMapping("/get")
    public ResponseEntity<String> testing(){
        return ResponseEntity.status(200).body("Hello this is for Testing the api application");
    }

    @PostMapping("add")
    public ResponseEntity<Dto> creatingRec(@RequestBody Dto dto){
       Relative relative = dtoMapper.dtoToRelative(dto);
        Presentation presentation = dtoMapper.dtoToPresentation(dto);
        // validate is pending
        relativeService.saveRelative(relative);
        presentationService.savePresentation(presentation);
        // pending
        return ResponseEntity.status(201).body(dto);
    }





}
