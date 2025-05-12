package org.api.events.controllers;



import org.springframework.web.bind.annotation.RestController;

@RestController("/ai")
public class Llama2AiController {

   // @Autowired
//   private ChatClient chatClient;
//  // public Llama2AiController(ChatClient.Builder chatClient) {
//       this.chatClient = chatClient.build();
//   }





//    @GetMapping("/{chat}")
//    public ResponseEntity<?> prompt(@PathVariable String chat){
//        String response = chatClient
//                .prompt(chat)
//                .call()
//                .content();
//        return ResponseEntity.status(200).body(response);
//    }
}
