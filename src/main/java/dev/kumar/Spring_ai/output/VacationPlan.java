package dev.kumar.Spring_ai.output;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPlan {


    private final ChatClient chatClient;


    public VacationPlan(ChatClient.Builder builder) {
        this.chatClient = builder.build();

    }


    @GetMapping("/vacation/unstructured")
    public String getVacationPlanUnstructured() {
        return chatClient.prompt()
                .user("suggest me some places in India to visit for a vacation")
                .call()
                .content();
    }


    @GetMapping("/vacation/structured")
    public Itninerary structured() {
        return chatClient.prompt().
                user("suggest me some places in India to visit for a vacation").
                call().
                entity(Itninerary.class);
    }
}
