package dev.kumar.Spring_ai.prompt;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    private final ChatClient chatClient;

    public ArticleController(ChatClient.Builder builder ) {

        this.chatClient = builder.build();
    }


    @GetMapping("/posts/new")
    public String newPost(@RequestParam(value = "topic" , defaultValue = "java latest release for multithreading ") String topic) {
        var system = "BLOG POST GENERATOR GUIDELINES " +
                "length & structure: The article should be between 800-1200 words, with a clear introduction, body, and conclusion. Use subheadings to organize the content and make it easy to read." +
                "tone & style: The tone should be informative and engaging, suitable for a general audience. Avoid jargon and technical language, and aim for a conversational style that keeps readers interested." +
                "content requirements: The article should provide a comprehensive overview of the topic, including key benefits, practical tips, and real-life examples. Use data and research to support your points, and include" +
                " actionable advice that readers can apply in their own lives. Ensure that the content is original and free of plagiarism." +
                "" +
                "ADd a title as well";

        return chatClient.prompt().user( u ->{ u.text("Write a blog post about {topic}");
        u.param("topic", topic);})
                .system(system)
                .call()
                .content();
    }
}
