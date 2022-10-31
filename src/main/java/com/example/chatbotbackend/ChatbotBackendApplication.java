package com.example.chatbotbackend;

import com.example.chatbotbackend.model.QA;
import com.example.chatbotbackend.repository.QARepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ChatbotBackendApplication {
  private final List<QA> questionsAnswers;
  private final QARepository qaRepository;

  public ChatbotBackendApplication(QARepository qaRepository) {
    this.qaRepository = qaRepository;
    questionsAnswers = this.qaRepository.findAll();
  }

  public static void main(String[] args) {
    SpringApplication.run(ChatbotBackendApplication.class, args);
  }

  @Bean
  CommandLineRunner runner() {
    return args -> System.out.println(questionsAnswers);
  }
}
