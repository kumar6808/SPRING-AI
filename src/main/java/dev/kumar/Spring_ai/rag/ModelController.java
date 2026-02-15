package dev.kumar.Spring_ai.rag;

import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
//import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelController {

    private final ChatClient chatClient;
    private final QuestionAnswerAdvisor ragAdvisor;

    public ModelController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.ragAdvisor = QuestionAnswerAdvisor
                .builder(vectorStore)
                .searchRequest(
                        SearchRequest.builder()
                                .topK(4)
                                .similarityThreshold(0.7)
                                .build()
                )
                .build();



        this.chatClient = builder.build();
    }

    @GetMapping("/rag/models")
    public Models faq(
            @RequestParam(value = "message", defaultValue = "Give me a list of all the models from OpenAI along with" +
                    "their  context window") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(Models.class);

    }

}
