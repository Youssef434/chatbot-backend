package com.example.chatbotbackend.controller;

import com.example.chatbotbackend.opennlpServicesFiles.FilePaths;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @GetMapping("/")
  public String helloResponse() {
    return null;
  }

  @GetMapping("/{lang}")
  public void chooseLanguage(@PathVariable String lang) {
    FilePaths.changeLanguage(lang);
  }

  @GetMapping("/{question}")
  public String ask(@PathVariable String question) {
    return null;
  }
}
