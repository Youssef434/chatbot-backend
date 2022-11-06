package com.example.chatbotbackend.controller;

import com.example.chatbotbackend.opennlpServicesFiles.FilePaths;
import com.example.chatbotbackend.service.nlp.LanguageService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class MainController {
  private final LanguageService languageService;

  public MainController(LanguageService languageService) {
    this.languageService = languageService;
  }

  @GetMapping(value = "/chat")
  public Map<String, Object> chatResponse(@RequestParam String question) throws IOException {
    return Map.of(
        "response", "Your Question is : " + question,
        "language",  languageService.detectLanguage(question),
        "timestamp", LocalDateTime.now()
    );
  }

  @GetMapping("/{lang}")
  public void chooseLanguage(@PathVariable String lang) {
    FilePaths.changeLanguage(lang);
  }

//  @GetMapping("/{question}")
//  public String ask(@PathVariable String question) {
//    return null;
//  }
}
