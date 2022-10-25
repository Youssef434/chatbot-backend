package com.example.chatbotbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ChatbotBackendApplication {
  Map<String, String> questionsAnswers = new HashMap<>();

  public static void main(String[] args) {
    SpringApplication.run(ChatbotBackendApplication.class, args);
  }

  @Bean
  CommandLineRunner runner() {
    return args -> {
      questionsAnswers.put("unknown", "Sorry, I didn't understand your question");
      questionsAnswers.put("greeting", "Hi there ! How can I help you ?");
      questionsAnswers.put("positive-feedback", "Is that all, is there anything else to help you with ?");
      questionsAnswers.put("shutdown", "Good bye, see you later");
      
    };
  }
}
