package org.api.events.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.api.events.constents.GettingType;
import org.api.events.constents.TotalReceivedType;
import org.api.events.dto.*;
import org.api.events.exceptions.NotFoundException;
import org.api.events.models.Presentation;
import org.api.events.models.Receiving;
import org.api.events.models.Relative;
import org.api.events.repo.RelativeRepo;
import org.api.events.service.presentationservice.IPresentationService;
import org.api.events.service.receivingservice.ReceivingService;
import org.api.events.service.relativeservice.IRelativeService;
import org.api.events.utils.IDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = {"http://localhost:5173/","https://d7e0-103-184-87-59.ngrok-free.app"})
@RequestMapping("/")
@Tag(name = "MAIN CONTROLLER FOR ALL BASIC INFO AND QUERYING", description = "All querying apis ")
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
    @Autowired
    private ReceivingService receivingService;
    @Autowired
    private Dto dto;

    /**
     *
     * <b>Testing api</b>
     * @return
     */
    @GetMapping("/get")
    @Operation(summary = "THIS IS FOR TESTING API",description = "This a testing api for testing the application....")
    public ResponseEntity<String> testing(){
        return ResponseEntity.status(200).body("Hello this is for Testing the api application");
    }




    /**
     * <b>Presentations </b>
     * @param dto
     * @return
     */
    @PostMapping("createPre")
    @Operation(summary = "THIS IS CRATING A NEW PRESENTATION",description = "User input taken from PRESENTATION form and save into db")
    public ResponseEntity<?> creatingPre(@RequestBody Dto dto){
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


    /**
     * <b>Completed this API don't Touch if you want to develop more you can use this</b>
     * @param dto
     * @return
     */
    @PostMapping("/createRec")
    @Operation(summary = "THIS IS CREATING A NEW RECEIVING",description = "User input capturing from user input form filling after save into db")
    public ResponseEntity<?> creatingRec(@RequestBody Dto dto){
        Relative relative = dtoMapper.dtoToRelative(dto);
        if(!relativeService.isRelative(relative)){
            relativeService.saveRelative(relative);
        }
        Receiving receiving = dtoMapper.dtoToReceiving(dto);
        receivingService.saveReceiving(receiving);
        return ResponseEntity.status(201).body(dto);
    }





    // -----------------------< GET All api >--------------------------------------


    /**
     * <b>Get all relatives</b>
     * @return
     */
    @GetMapping("/getallrel")  // the Response not cleared fix the issue
    @Operation(summary = "THIS API WORKS FOR GETTING ALL RELATIVES",description = "If you want to know how many relatives he have then," +
            " this api fetch all relatives from db...")
    public ResponseEntity<List<Relative>> getAllRelative() {
        return ResponseEntity.status(200).body(relativeService.getAllRelatives());
    }

    /**
     * <b>Get all Presentations</b>
     * @return
     */
    @GetMapping("/allpre")
    @Operation(summary = "THIS API FOR GETTING ALL PRESENTATIONS LIFE TIME",description = "If User want to know how many Presentations getting from relatives")
    public ResponseEntity<List<RespPresentationDto>> getAllPresentations() {
        List<Presentation> pres = presentationService.getAll();
        List<RespPresentationDto> resp = dtoMapper.presentationToRespDtoList(pres);
        return ResponseEntity.status(200).body(resp);
    }

    /**
     * <b>Get all Receiver's </b>
     * @return
     */
    @GetMapping("/getallrec")
    public ResponseEntity<List<RespRecivingDTO>> getAllReceiving() {
        List<Receiving> rev = receivingService.getAllRecivings();
        List<RespRecivingDTO> respDto = dtoMapper.recivingToRespDtoList(rev);
        return ResponseEntity.status(200).body(respDto);
    }


    /**
     * <b>Get all city's </b>
     * @return
     */
    @GetMapping("/getallcity")
    public ResponseEntity<List<String>> getAllCity() {
        List<String> listCitys = relativeService.getAllUniqueCitys();
        return ResponseEntity.status(200).body(listCitys);
    }

    // total gold silver amount sum not count
    @GetMapping("/totalrec")
    public ResponseEntity<?> totals(@RequestParam(required = true, value = "type") TotalReceivedType type,
                                    @RequestParam(required = true,value = "getType") GettingType getType) {

        TotalReceived resp = new TotalReceived();
        if (type.equals(TotalReceivedType.GOLD_IN_GM) && getType.equals(GettingType.RECEIVING)){
            resp.setReceived_gm_or_INR(receivingService.getTotalGold());
            resp.setType(TotalReceivedType.GOLD_IN_GM);
        }else if(type.equals(TotalReceivedType.GOLD_IN_GM) && getType.equals(GettingType.PRESENTATION)) {
            resp.setReceived_gm_or_INR(presentationService.getTotalGold());
            resp.setType(TotalReceivedType.GOLD_IN_GM);
        }else if(type.equals(TotalReceivedType.SILVER_IN_GM) && getType.equals(GettingType.RECEIVING) ) {
            resp.setReceived_gm_or_INR(receivingService.getTotalSliver());
            resp.setType(TotalReceivedType.SILVER_IN_GM);
        }else if(type.equals(TotalReceivedType.SILVER_IN_GM) && getType.equals(GettingType.PRESENTATION) ) {
            resp.setReceived_gm_or_INR(presentationService.getTotalSliver());
            resp.setType(TotalReceivedType.SILVER_IN_GM);
        }else if(type.equals(TotalReceivedType.AMOUNT_IN_INR) && getType.equals(GettingType.RECEIVING) ) {
            resp.setReceived_gm_or_INR(receivingService.getTotalAmount());
            resp.setType(TotalReceivedType.AMOUNT_IN_INR);
        }else if(type.equals(TotalReceivedType.AMOUNT_IN_INR) && getType.equals(GettingType.PRESENTATION) ) {
            resp.setReceived_gm_or_INR(presentationService.getTotalAmount());
            resp.setType(TotalReceivedType.AMOUNT_IN_INR);
        }
        return ResponseEntity.status(200).body(resp);
    }


    /**
     * <b>Testing was successfully working Now you can try this methods no need to use dto mappings and extra layers</b>
     * @return AllCityDTO
     */

    @GetMapping("/dto") // cityDTO
    public ResponseEntity<?> testingDTOResponce(){
        List<AllCitysDto> dto = relativeService.getAllCitys();
        return ResponseEntity.status(200).body(dto);
    }


    @GetMapping("/getrelbycity")
    public ResponseEntity<?> getRelativesAndPresentationsByCity(@RequestParam(value = "city",
                                                                required = true ) String city,
                                                                @RequestParam(required = true) GettingType type) {
        List<RelativeByCityPreDto> response = null;
        if(type.equals(GettingType.RECEIVING)){
            response = relativeService.getRelativeByCityReceiving(city);
        }else{
            response = relativeService.getRelativeByCityPresenations(city);
        }
        if(response == null){
            throw new NotFoundException("not found data");
        }
        return ResponseEntity.status(200).body(response);
    }


    @GetMapping("/totalgifts")
    public ResponseEntity<?> getRelativeByGifts(@RequestParam(required = true) GettingType type){

        List<GiftsFromRelatives> response = null;
        if(type.equals(GettingType.RECEIVING)){
            response = relativeService.getGiftsFromRelativesReceiving();
        }else{
            response = relativeService. getGiftsFromRelativesPresenations();
        }
        if(response == null){
            throw new NotFoundException("not found data");
        }
        return ResponseEntity.status(200).body(response);
    }


    @GetMapping("/totalgold")
    public ResponseEntity<?> getRelativeByGold(@RequestParam(required = true) GettingType type){

        List<GoldFromRelatives> response = null;
        if(type.equals(GettingType.RECEIVING)){
            response = relativeService.getGoldFromRelativesReceiving();
        }else{
            response = relativeService.getGoldFromRelativesPresenations();
        }
        if(response == null){
            throw new NotFoundException("not found data");
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/totalsilver")
    public ResponseEntity<?> getRelativeBySilver(@RequestParam(required = true) GettingType type){

        List<SilverFromRelatives> response = null;
        if(type.equals(GettingType.RECEIVING)){
            response = relativeService.getSilverFromRelativesReceiving();
        }else{
            response = relativeService.getSilverFromRelativesPresenations();
        }
        if(response == null){
            throw new NotFoundException("not found data");
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/totalamount")
    public ResponseEntity<?> getRelativeByAmount(@RequestParam(required = true) GettingType type){

        List<AmountFromRelatives> response = null;
        if(type.equals(GettingType.RECEIVING)){
            response = relativeService. getAmountFromRelativesReceiving();
        }else{
            response = relativeService.getAmountFromRelativesPresenations();
        }
        if(response == null){
            throw new NotFoundException("not found data");
        }
        return ResponseEntity.status(200).body(response);
    }





}
