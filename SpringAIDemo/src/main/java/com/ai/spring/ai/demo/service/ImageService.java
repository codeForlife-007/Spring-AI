package com.ai.spring.ai.demo.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

  private final OpenAiImageModel openAiImageModel;

  public ImageService(OpenAiImageModel openAiImageModel) {
    this.openAiImageModel = openAiImageModel;
  }

  public ImageResponse generateImage(String prompt, String quality, int n, int width, int height) {
    return openAiImageModel.call(
            new ImagePrompt(prompt,
                    OpenAiImageOptions
                            .builder()
                            .withQuality(quality)
                            .withN(n)
                            .withHeight(height)
                            .withWidth(width)
                            .withModel("dall-e-3")
                            .withStyle("natural")
                            .build()
                            ));
  }
}