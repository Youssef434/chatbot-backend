package com.example.chatbotbackend.controller;

import com.example.chatbotbackend.opennlpServicesFiles.FilePaths;
import com.example.chatbotbackend.repository.QARepository;
import com.example.chatbotbackend.service.nlp.LanguageService;
import com.example.chatbotbackend.service.nlp.TokenizeService;
import com.example.chatbotbackend.service.nlp.model.Model;
import opennlp.tools.doccat.DoccatModel;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class MainController {
  private final LanguageService languageService;
  private TokenizeService tokenizeService;
  private QARepository qaRepository;
  private static Model theModel;

  public MainController(LanguageService languageService, TokenizeService tokenizeService, QARepository qaRepository) {
    this.languageService = languageService;
    this.tokenizeService = tokenizeService;
    this.qaRepository = qaRepository;
    theModel = new Model(qaRepository, tokenizeService);
  }

  @GetMapping(value = "/chat")
  public Map<String, Object> chatResponse(@RequestParam String question) throws IOException {
    FilePaths.changeLanguage(languageService.detectLanguage(question));
    String[] sentences = theModel.decomposeToSentences(question);

    String answer = Arrays.stream(sentences)
        .map(MainController::sentenceMapper)
        .collect(Collectors.joining(", "));

    return Map.of(
        "response", answer,
        "language",  FilePaths.lang,
        "timestamp", LocalDateTime.now()
    );
  }

  private static String sentenceMapper(String sentence) {
    try {
      var questionsAnswers = theModel.loadQuestions();
      DoccatModel doccatModel = theModel.trainModel();
      String[] tokens = theModel.decomposeToWords(sentence);
      String category = theModel.detectCategory(doccatModel, tokens);

      return questionsAnswers
          .stream()
          .filter(qa -> qa.getQuestion().equals(category))
          .findFirst()
          .orElseThrow()
          .getQuestion();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
