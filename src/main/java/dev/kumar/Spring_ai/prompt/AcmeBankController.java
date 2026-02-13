package dev.kumar.Spring_ai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme")
public class AcmeBankController {

    private final ChatClient chatClient;

    public AcmeBankController(ChatClient.Builder builder) {
        this.chatClient = builder.build();

    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {

        var systemInstruction = "You are a helpful assistant for Acme Bank. " +
                "you can only discuss or Answer customer queries about account information, transactions, and services."
                +
                "if asked about anything else, politely decline and steer the conversation back to banking-related topics.";

        return chatClient.prompt()
                .user(message)
                .system(systemInstruction)
                .call()
                .content();
    }

}
