package com.example.chatbotbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Conversation {

  @Id
  private int id;

  private static final class QA {
    private final String question;
    private final String answer;

    public QA(String question, String answer) {
      this.question = question;
      this.answer = answer;
    }
  }
  private List<QA> questionsAnswers;

  public Conversation() {
    questionsAnswers = new ArrayList<>();
  }

  public void logQA(String question, String answer) {
    questionsAnswers.add(new QA(question, answer));
  }

  public void persist() {

  }


}
