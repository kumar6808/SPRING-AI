package dev.kumar.Spring_ai.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();

    }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/chat")
    public String chat(String message) {
        return chatClient.prompt().user("tell me the latest news about the Ai  in the 3 lines ")
                .call().content();

    }

    // @GetMapping("/stream")
    // public Flux<String> stream() {
    // return chatClient.prompt()
    // .user("i am visiting ujjain . Can you give me 10 places that must needs to be
    // visited ").stream().
    // ;

    // }

    @GetMapping("/stream ")
    public Flux<String> stream() {
        return chatClient.prompt()
                .user("i am visiting ujjain . Can you give me 10 places that must needs to be visited ").stream()
                .content();
    }

    @GetMapping("/joke")
    public ChatResponse joke() {
        return chatClient.prompt().user("tell me a joke about the Ai ").call().chatResponse();
    }

}
