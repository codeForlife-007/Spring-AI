package com.ai.spring.ai.demo.controller;

import com.ai.spring.ai.demo.service.ChatService;
import com.ai.spring.ai.demo.service.ImageService;
import com.ai.spring.ai.demo.service.RecipeService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenAIController {

  private final ChatService chatService;
  private final ImageService imageService;
  private final RecipeService recipeService;

  public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
    this.chatService = chatService;
    this.imageService = imageService;
    this.recipeService = recipeService;
  }

  @GetMapping("ask-ai")
  public String getResponse(@RequestParam String prompt) {
    return chatService.getResponse(prompt);
  }

  @GetMapping("ask-ai-options")
  public String getResponseOptions(@RequestParam String prompt) {
    return chatService.getResponseOptions(prompt);
  }

  @GetMapping("generate-image")
  public List<String> generateImage(@RequestParam String prompt,
                              @RequestParam(defaultValue = "hd") String quality,
                              @RequestParam(defaultValue = "1") int n,
                              @RequestParam(defaultValue = "1024") int width,
                              @RequestParam(defaultValue = "1024") int height) {
    ImageResponse imageResponse = imageService.generateImage(prompt, quality, n, width, height);
    return imageResponse.getResults().stream()
            .map(result -> result.getOutput().getUrl())
           .toList();
  }

  @GetMapping("recipe-creator")
  public String createRecipe(@RequestParam String ingredients,
                             @RequestParam(defaultValue = "any") String cuisine,
                             @RequestParam(defaultValue = "") String dietaryRestrictions) {
    return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
  }
}