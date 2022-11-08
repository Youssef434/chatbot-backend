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
    return args -> {
//      qaRepository.deleteAll();

//      List<QA> qas = List.of(
//          new QA("unknown", Map.of(
//           "en", "Sorry, I didn't understand your question",
//           "fr", "Pardon, j'ai pas compris ta question"
//          )),
//          new QA("greeting", Map.of(
//              "en", "Hello, how can I help you?",
//              "fr", "Salut, comment je peux vous aider ?"
//          )),
//          new QA("services", Map.of(
//              "fr", "pédiatrie, dermatologie, diabétique, neurologue",
//              "en", "pediatric, dermatology, diabetic, neurologist"
//          ))
//      );

//      qaRepository.saveAll(qas);

//      qaRepository.save(
//          new QA("costs", Map.of(
//              "en", "one apointment costs 200 dh",
//              "fr", "une séance vaut 200 dh"
//          ))
//      );

    };
  }
}
