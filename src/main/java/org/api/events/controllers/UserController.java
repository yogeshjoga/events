package org.api.events.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.api.events.models.User;
import org.api.events.service.userservice.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/","https://ef14-103-184-87-57.ngrok-free.app","http://localhost:5173/"})
@RequestMapping("/user")
@Tag(name = "USER CONTROLLER", description = "All user related apis ")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @GetMapping("/getuser/{email}")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> getCurrentUser(@PathVariable(value = "email") String email) {
       User user = userService.getUserByEmail(email);
       log.info("\u001B[1;31m :: Testing API  "+email +"  :: \u001B[0m");
       return ResponseEntity.ok(user);
    }



}
