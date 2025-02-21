package org.api.events.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.api.events.constents.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = {"http://localhost:5173/","https://ef14-103-184-87-57.ngrok-free.app"})
@Tag(name = "THIS IS THE TESTING REST CONTROLLER", description = "THIS IS FOR ONLY TESTING")
public class TestAPIController {


    private static final Logger log = LoggerFactory.getLogger(TestAPIController.class);

    /**
     *
     * <b>Testing api</b>
     * @return
     */
    @GetMapping("/get")
    @Operation(summary = "THIS IS FOR TESTING API",description = "This a testing api for testing the application...." +
            "The choice between using headers or cookies to get user data such as user ID and token depends on several factors, including your application's security requirements, how you handle authentication, and what makes the most sense for your architecture.\n" +
            "\n" +
            "Here are the key points for both methods:\n" +
            "\n" +
            "Using Headers\n" +
            "Security: Headers are not stored on the client side after the request is made, which can reduce the risk of client-side attacks.\n" +
            "\n" +
            "Stateless: Using headers supports stateless requests, which are a common feature of RESTful APIs.\n" +
            "\n" +
            "Control: You have more control over when and how headers are sent with each request.\n" +
            "\n" +
            "Using Cookies\n" +
            "Client-Side Storage: Cookies are stored on the client side, and they can persist across sessions.\n" +
            "\n" +
            "Ease of Use: Cookies are automatically sent with every HTTP request to the server, so you don't have to manually include them in each request.\n" +
            "\n" +
            "Cross-Origin Requests: Cookies can be tricky with cross-origin requests (CORS), which can require additional configuration.\n" +
            "\n" +
            "Which to Use?\n" +
            "For APIs: Headers are often the preferred method for APIs because they promote statelessness and give you more control over authentication and authorization.\n" +
            "\n" +
            "For Web Applications: Cookies can be more convenient for web applications where maintaining session state across multiple requests is necessary.\n" +
            "\n" +
            "Ultimately, the best approach depends on your specific needs and security considerations. For example, if you're building a RESTful API, headers are generally a better choice. For traditional web applications, cookies might be more convenient.")
    public ResponseEntity<String> testing(@RequestHeader HttpHeaders headers ) {
        String userId = headers.getFirst(String.valueOf(Headers.USER_ID));
        log.info("\u001B[1;31m :: Testing API  "+userId +"  :: \u001B[0m");
        return ResponseEntity.status(200).body("Hello this is for Testing the api application   ----<  "+userId);
    }

}
