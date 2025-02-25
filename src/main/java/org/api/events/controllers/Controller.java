package org.api.events.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Null;
import org.api.events.constents.GettingType;
import org.api.events.constents.Headers;
import org.api.events.constents.TotalReceivedType;
import org.api.events.dto.*;
import org.api.events.exceptions.NotFoundException;
import org.api.events.exceptions.RelativeNotFoundException;
import org.api.events.models.*;
import org.api.events.repo.RelativeRepo;
import org.api.events.service.goldapi.IGoldSilverService;
import org.api.events.service.presentationservice.IPresentationService;
import org.api.events.service.receivingservice.ReceivingService;
import org.api.events.service.relativeservice.IRelativeService;
import org.api.events.service.userservice.UserService;
import org.api.events.utils.IDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@CrossOrigin(origins = {"${front_end_url}","${ngrok_url}"})
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

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private IGoldSilverService goldSilverService;


    /**
     *
     *
     */
    private static UUID getUserIdByUuid( HttpHeaders headers) {
        // Getting the UserId from @RequestHeaders
        String strHeader = headers.getFirst(String.valueOf(Headers.USER_ID));
        UUID userId = null;
        if(strHeader != null){
            userId = UUID.fromString(strHeader);
        }
        return userId;
        // UUID userId = getUserIdByUuid(headers);
    }



    /**
     * <b>Presentations </b>
     * @param dto
     * @return
     */
    @PostMapping("createPre")
    @Operation(summary = "THIS IS CRATING A NEW PRESENTATION",
            tags = {"API Documentation", "Testing Completed"},
            description = "User input taken from PRESENTATION form and save into db")
    public ResponseEntity<?> creatingPre(@RequestBody Dto dto, @RequestHeader HttpHeaders headers){
        Dto responce = dto;
        UUID userId = getUserIdByUuid(headers);
        User user = userService.getUserById(userId);
        Optional<Relative> relative1 =  relativeService.getRelative(dto.getFirstName(), dto.getLastName(), dto.getCity(),userId);
        if(relative1.isEmpty()){
            // create a new relative otherwise just add presentation
            // and attach with relative id and user to presentation
            Relative relative = dtoMapper.dtoToRelative(dto, user);
            Presentation presentation = dtoMapper.dtoToPresentation(dto,user);
            relativeService.saveRelative(relative);
            presentation.setRelative(relative);
            presentationService.savePresentation(presentation);
            log.info("\u001B[1;31m :: Testing relative :: \u001B[0m");
        }
        log.info("\u001B[1;31m :: relative :: \u001B[0m");

        Presentation presentation = dtoMapper.dtoToPresentation(dto,user);
        // Lambda
        relative1.ifPresent(presentation::setRelative);
        presentationService.savePresentation(presentation);
        return ResponseEntity.status(201).body(responce);
    }


    /**
     * <b>Completed this API don't Touch if you want to develop more you can use this</b>
     * @param dto
     * @return
     */
    @PostMapping("/createRec")
    @Operation(summary = "THIS IS CREATING A NEW RECEIVING",description = "User input capturing from user input form filling after save into db")
    public ResponseEntity<?> creatingRec(@RequestBody Dto dto, @RequestHeader HttpHeaders headers){
        UUID userId = getUserIdByUuid(headers);
        User user = userService.getUserById(userId);
        Optional<Relative> relative1 =  relativeService.getRelative(dto.getFirstName(), dto.getLastName(), dto.getCity(),userId);
        if(relative1.isEmpty()){
            // create a new relative otherwise just add presentation
            // and attach with relative id and user to presentation
            Relative relative = dtoMapper.dtoToRelative(dto, user);
            Receiving receiving = dtoMapper.dtoToReceiving(dto,user,relative);
            relativeService.saveRelative(relative);
            receivingService.saveReceiving(receiving);
            log.info("\u001B[1;31m :: Relative and Receiving saved relative :: \u001B[0m");
        }else {
            Receiving receiving = dtoMapper.dtoToReceiving(dto, user, relative1.get());
            receivingService.saveReceiving(receiving);
            log.info("\u001B[1;31m :: RECEIVING  SAVED INTO DATABASE :: \u001B[0m");
        }
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
    public ResponseEntity<?> getAllRelative(@RequestHeader HttpHeaders headers) {
        UUID userId = getUserIdByUuid(headers);
        return ResponseEntity.status(200).body(relativeService.getAllRelatives(userId));
    }

    /**
     * <b>Get all Presentations</b>
     * @return
     */
    @GetMapping("/allpre")
    @Operation(summary = "THIS API FOR GETTING ALL PRESENTATIONS LIFE TIME",description = "If User want to know how many Presentations getting from relatives")
    public ResponseEntity<List<RespPresentationDto>> getAllPresentations(@RequestHeader HttpHeaders headers) {
        UUID userId = getUserIdByUuid(headers);
        List<Presentation> pres = presentationService.getAll(userId);
        List<RespPresentationDto> resp = dtoMapper.presentationToRespDtoList(pres);
        return ResponseEntity.status(200).body(resp);
    }

    /**
     * <b>Get all Receiver's </b>
     * @return
     */
    @GetMapping("/getallrec")
    public ResponseEntity<List<RespRecivingDTO>> getAllReceiving(@RequestHeader HttpHeaders headers) {
        UUID userId = getUserIdByUuid(headers);
        List<Receiving> rev = receivingService.getAllRecivings(userId);
        List<RespRecivingDTO> respDto = dtoMapper.recivingToRespDtoList(rev);
        return ResponseEntity.status(200).body(respDto);
    }


    /**
     * <b>Get all city's </b>
     * @return
     */
    @GetMapping("/getallcity")
    public ResponseEntity<?> getAllCity(@RequestHeader HttpHeaders headers) {
        UUID userId = getUserIdByUuid(headers);
     //   List<String> listCitys = relativeService.getAllUniqueCitys(userId);getAllCitys
        return ResponseEntity.status(200).body(relativeService.getAllCitys());
    }

    // total gold silver amount sum not count
    @GetMapping("/totalrec")
    public ResponseEntity<?> totals(@RequestParam(required = true, value = "TOTAL_TYPE") TotalReceivedType type,
                                    @RequestParam(required = true,value = "TYPE") GettingType getType,
                                    @RequestHeader HttpHeaders headers) {

        UUID userId = getUserIdByUuid(headers);

        TotalReceived resp = new TotalReceived();
        if (type.equals(TotalReceivedType.GOLD_IN_GM) && getType.equals(GettingType.RECEIVING)){
            resp.setReceived_gm_or_INR(receivingService.getTotalGold(userId));
            resp.setType(TotalReceivedType.GOLD_IN_GM);
        }else if(type.equals(TotalReceivedType.GOLD_IN_GM) && getType.equals(GettingType.PRESENTATION)) {
            resp.setReceived_gm_or_INR(presentationService.getTotalGold(userId));
            resp.setType(TotalReceivedType.GOLD_IN_GM);
        }else if(type.equals(TotalReceivedType.SILVER_IN_GM) && getType.equals(GettingType.RECEIVING) ) {
            resp.setReceived_gm_or_INR(receivingService.getTotalSliver(userId));
            resp.setType(TotalReceivedType.SILVER_IN_GM);
        }else if(type.equals(TotalReceivedType.SILVER_IN_GM) && getType.equals(GettingType.PRESENTATION) ) {
            resp.setReceived_gm_or_INR(presentationService.getTotalSliver(userId));
            resp.setType(TotalReceivedType.SILVER_IN_GM);
        }else if(type.equals(TotalReceivedType.AMOUNT_IN_INR) && getType.equals(GettingType.RECEIVING) ) {
            resp.setReceived_gm_or_INR(receivingService.getTotalAmount(userId));
            resp.setType(TotalReceivedType.AMOUNT_IN_INR);
        }else if(type.equals(TotalReceivedType.AMOUNT_IN_INR) && getType.equals(GettingType.PRESENTATION) ) {
            resp.setReceived_gm_or_INR(presentationService.getTotalAmount(userId));
            resp.setType(TotalReceivedType.AMOUNT_IN_INR);
        }
        return ResponseEntity.status(200).body(resp);
    }


    /**
     *
     * @param headers
     * @param firstName
     * @param lastName
     * @param city
     * @return
     */
    @GetMapping("getrelativebyname")
    public ResponseEntity<?> getRelativeByName(@RequestHeader (required = true)HttpHeaders headers,
                                               @RequestParam (required = true, value = "firstName") String firstName,
                                               @RequestParam (required = true, value = "lastName") String lastName,
                                               @RequestParam(required = true, value = "city") String city) {
        UUID userId = getUserIdByUuid(headers);
        Relative relative = relativeService.getRelativeByFullNameAndCity(firstName,lastName,city,userId);
        if(relative == null) {
            throw new RelativeNotFoundException("Relative not found.. Or else enter correct name, city");
        }
        ResponseRelativeData relData = objectMapper.convertValue(relative, ResponseRelativeData.class);
        return ResponseEntity.status(200).body(relData);
    }


    /**
     * <b>Testing was successfully working Now you can try these methods no need to use dto mappings and extra layers</b>
     * @return AllCityDTO
     */
    @GetMapping("/dto") // cityDTO
    public ResponseEntity<?> testingDTOResponce(@RequestHeader(required = true) HttpHeaders headers){
        List<AllCitysDto> dto = relativeService.getAllCitys();
        return ResponseEntity.status(200).body(dto);
    }

    /**
     *
     * @param city
     * @param type
     * @param headers
     * @return
     */
    @GetMapping("/getrelbycity")
    public ResponseEntity<?> getRelativesAndPresentationsByCity(@RequestParam(value = "CITY",
                                                                required = true ) String city,
                                                                @RequestParam(value = "TYPE", required = true) GettingType type,
                                                                @RequestHeader HttpHeaders headers) {
        UUID userId = getUserIdByUuid(headers);
        List<RelativeByCityPreDto> response = null;
        if(type.equals(GettingType.RECEIVING)){
            response = relativeService.getRelativeByCityReceiving(city,userId);
        }else{
            response = relativeService.getRelativeByCityPresenations(city,userId);
        }
        if(response == null){
            throw new NotFoundException("not found data");
        }
        return ResponseEntity.status(200).body(response);
    }

// test success
    @GetMapping("/totalgifts")
    public ResponseEntity<?> getRelativeByGifts(@RequestParam(value = "TYPE", required = true) GettingType type,
                                                @RequestHeader HttpHeaders headers){
        UUID userId = getUserIdByUuid(headers);
        List<GiftsFromRelatives> response = null;
        if(type.equals(GettingType.RECEIVING)){
            response = relativeService.getGiftsFromRelativesReceiving(userId);
        }else{
            response = relativeService. getGiftsFromRelativesPresenations(userId);
        }
        if(response == null){
            throw new NotFoundException("not found data");
        }
        return ResponseEntity.status(200).body(response);
    }


    @GetMapping("/totalgold")
    public ResponseEntity<?> getRelativeByGold(@RequestParam(value = "TYPE", required = true) GettingType type,
                                               @RequestHeader HttpHeaders headers){

        UUID userId = getUserIdByUuid(headers);
        List<GoldFromRelatives> response = null;
        if(type.equals(GettingType.RECEIVING)){
            response = relativeService.getGoldFromRelativesReceiving(userId);
        }else{
            response = relativeService.getGoldFromRelativesPresenations(userId);
        }
        if(response == null){
            throw new NotFoundException("not found data");
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/totalsilver")
    public ResponseEntity<?> getRelativeBySilver(@RequestParam(value = "TYPE", required = true) GettingType type,
                                                 @RequestHeader HttpHeaders headers){

        UUID userId = getUserIdByUuid(headers);
        List<SilverFromRelatives> response = null;
        if(type.equals(GettingType.RECEIVING)){
            response = relativeService.getSilverFromRelativesReceiving(userId);
        }else{
            response = relativeService.getSilverFromRelativesPresenations(userId);
        }
        if(response == null){
            throw new NotFoundException("not found data");
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/totalamount")
    public ResponseEntity<?> getRelativeByAmount(@RequestParam(value = "TYPE", required = true) GettingType type,
                                                 @RequestHeader HttpHeaders headers){


        UUID userId = getUserIdByUuid(headers);
        List<AmountFromRelatives> response = null;
        if(type.equals(GettingType.RECEIVING)){
            response = relativeService. getAmountFromRelativesReceiving(userId);
        }else{
            response = relativeService.getAmountFromRelativesPresenations(userId);
        }
        if(response == null){
            throw new NotFoundException("not found data");
        }
        return ResponseEntity.status(200).body(response);
    }



    // charts apis

    @GetMapping("/topfivePreGold")
    public ResponseEntity<?> goldPreWithNameTopFiveRelatives(@RequestHeader(required = true)  HttpHeaders headers){
        UUID userId = getUserIdByUuid(headers);
        if(userId == null){
            throw new RelativeNotFoundException("Relative not found.. Or else enter correct name, city");
        }
        List<TopFiveRelatives> response = presentationService.getGoldWithNameTopFiveRelatives(userId);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/topfivePreSilver")
    public ResponseEntity<?> silverPreWithNameTopFiveRelatives(@RequestHeader(required = true)  HttpHeaders headers){
        UUID userId = getUserIdByUuid(headers);
        if(userId == null){
            throw new RelativeNotFoundException("Relative not found.. Or else enter correct name, city");
        }
        List<TopFiveRelatives> response = presentationService.getSilverWithNameTopFiveRelatives(userId);
        return ResponseEntity.status(200).body(response);
    }



    @GetMapping("/topfiveRecGold")
    public ResponseEntity<?> GoldRecWithNameTopFiveRelatives(@RequestHeader(required = true)  HttpHeaders headers){
        UUID userId = getUserIdByUuid(headers);
        if(userId == null){
            throw new RelativeNotFoundException("Relative not found.. Or else enter correct name, city");
        }
        List<TopFiveRelatives> response = receivingService.getSilverWithNameTopFiveRelatives(userId);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/topfiveRecSilver")
    public ResponseEntity<?> silverRecWithNameTopFiveRelatives(@RequestHeader(required = true)  HttpHeaders headers){
        UUID userId = getUserIdByUuid(headers);
        if(userId == null){
            throw new RelativeNotFoundException("Relative not found.. Or else enter correct name, city");
        }
        List<TopFiveRelatives> response = receivingService.getGoldWithNameTopFiveRelatives(userId);
        return ResponseEntity.status(200).body(response);
    }





    // List of objects

   // public ResponseEntity<?>



    // test java







    // Gold_Silver Rates

    /**
     *
     * @return
     */
    @GetMapping("/getmetalprice")
    public ResponseEntity<List<ResponceMetalRates>> getMetalPrice(){
        List<ResponceMetalRates> metalRates = new ArrayList<>();
        // getting Gold rates
        ResponceMetalRates response = objectMapper.convertValue( goldSilverService.getGoldRates(),
                ResponceMetalRates.class);
        // Getting Silver Rates
        ResponceMetalRates response1 = objectMapper.convertValue( goldSilverService.getSilverRates(),
                ResponceMetalRates.class);

        metalRates.add(response);
        metalRates.add(response1);
        return ResponseEntity.status(200).body(metalRates);
    }



}
