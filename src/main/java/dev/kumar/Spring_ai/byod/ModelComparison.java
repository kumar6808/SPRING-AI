package dev.kumar.Spring_ai.byod;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelComparison {


    private final ChatClient chatClient;

    public ModelComparison( ChatClient.Builder builder) {
        this.chatClient = builder.build() ;
    }


    @GetMapping("/models")
    public String models() {
        return chatClient.prompt()
                .user("What are the differences between gpt models available ? What are the " +
                        "their current context window size ?")
                .call()
                .content();
    }


    @GetMapping("/models/stuff-the-prompt")
    public String modelsStuffThePrompt() {


        var system = """
                
                if you are asked about the differences between gpt models available and their current context window size, please provide a detailed comparison in a tabular format. Include the company, model name (flagship or latest), context window size, and any relevant notes about the model's capabilities or use cases. Here is some  information to help
                with your response .
                | **Company**                      | **Model (Flagship / Latest)**     | **Context Window**                                     | **Notes**                                                                                        |
                | -------------------------------- | --------------------------------- | ------------------------------------------------------ | ------------------------------------------------------------------------------------------------ |
                | **OpenAI**                       | **GPT-5.2**                       | ~400,000 tokens                                        | Large extended context for deep reasoning & codebases; 128k max output. ([OpenAI Developers][1]) |
                | **Google DeepMind**              | **Gemini 3 / Gemini 3 Pro**       | ~1,000,000 tokens                                      | Massive long-context support; best for multimodal + extended input. ([Kanerika][2])              |
                | **Anthropic**                    | **Claude Opus 4.6 (Enterprise)**  | ~1,000,000 tokens                                      | Beta long-context window; strong for enterprise & coding tasks. ([IT Pro][3])                    |
                | **Anthropic**                    | **Claude Opus 4.5**               | ~200,000 tokens standard (extended to 1M in some APIs) | Core long-context version before Opus 4.6. ([Kanerika][2])                                       |
                | **OpenAI (prior)**               | **GPT-5 (original)**              | ~256,000 tokens                                        | Older flagship before 5.2; still substantial. ([WIRED][4])                                       |
                | **Various Open-Source & Others** | **LLaMA / Qwen / Smaller Models** | ~4,000–32,000+ tokens                                  | Typical range for smaller open-source families unless specially extended. ([Medium][5])          |
                
                [1]: https://developers.openai.com/api/docs/models/gpt-5.2?utm_source=chatgpt.com "GPT-5.2 Model | OpenAI API"
                [2]: https://kanerika.com/blogs/chatgpt-5-2-vs-gemini-3-vs-claude-opus-4-5/?utm_source=chatgpt.com "ChatGPT 5.2 vs Gemini 3 vs Claude Opus 4.5 Comparison"
                [3]: https://www.itpro.com/technology/artificial-intelligence/anthropic-reveals-claude-opus-4-6-enterprise-focused-model-1-million-token-context-window?utm_source=chatgpt.com "Anthropic reveals Claude Opus 4.6, an enterprise-focused model with 1 million token context window for extended code capabilities"
                [4]: https://www.wired.com/story/openais-gpt-5-is-here?utm_source=chatgpt.com "OpenAI Finally Launched GPT-5. Here's Everything You Need to Know"
                [5]: https://medium.com/%40aryadav.2810/a-detailed-comparison-of-all-llms-in-2025-gpt-vs-gemini-vs-deepseek-vs-llama-vs-claude-and-more-f54b576c77d4?utm_source=chatgpt.com "A Detailed Comparison of all LLMs in 2025 - GPT vs ..."
                
                """;
        return chatClient.prompt()
                .user("What are the differences between gpt models available ? What are the " +
                        "their current context window size ?")
                .system(system)
                .call()
                .content();
    }

}
