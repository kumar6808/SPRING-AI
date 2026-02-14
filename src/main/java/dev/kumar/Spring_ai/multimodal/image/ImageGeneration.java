package dev.kumar.Spring_ai.multimodal.image;


import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class ImageGeneration {


    private final OpenAiImageModel imageModel;


    public ImageGeneration(OpenAiImageModel  imageModel) {
        this.imageModel = imageModel;

    }


    @GetMapping("/generate-image")
    public ResponseEntity<Map<String, String>> generateImage(
            @RequestParam(defaultValue = "A serene landscape with mountains and a lake during sunset") String prompt) {
        ImageOptions options = OpenAiImageOptions.builder()
                .quality("hd")
                .model("dall-e-3")
                .height(1024)
                .style("vivid")
                .width(1024)
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(prompt, options);
        ImageResponse imageResponse = imageModel.call(imagePrompt);

        String url = imageResponse.getResult().getOutput().getUrl();

        return ResponseEntity.ok(Map.of("prompt", prompt, "imageURl", url));
    }


    }
