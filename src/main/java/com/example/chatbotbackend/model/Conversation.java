package com.example.chatbotbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Conversation {
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
