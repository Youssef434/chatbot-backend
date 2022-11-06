package com.example.chatbotbackend.model;

import com.example.chatbotbackend.service.nlp.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;

@Document
public class QA {
  private final String question;
  private final Map<String, String> answer;

  public QA(String question, Map<String, String> answer) {
    this.question = question;
    this.answer = answer;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", QA.class.getSimpleName() + "[", "]")
        .add("question='" + question + "'")
        .add("answer='" + answer + "'")
        .toString();
  }
}
