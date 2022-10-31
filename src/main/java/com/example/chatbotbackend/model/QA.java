package com.example.chatbotbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.StringJoiner;

@Document
public class QA {
  private final String question;
  private final String answer;

  public QA(String question, String answer) {
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
