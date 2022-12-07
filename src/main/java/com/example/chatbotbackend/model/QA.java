package com.example.chatbotbackend.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

@Document
public final class QA {
  private final String question;
  private final Map<String, String> answer;

  public QA(String question, Map<String, String> answer) {
    this.question = question;
    this.answer = answer;
  }
  public String getQuestion() {
    return question;
  }

  public String getAnswer(String lang) {
    return answer.get(lang);
  }

  public void addAnswer(String lang, String answerValue) {
    answer.put(lang, answerValue);
  }

  public Set<Map.Entry<String, String>> getAnswersSet() {
    return answer.entrySet();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", QA.class.getSimpleName() + "[", "]")
        .add("question='" + question + "'")
        .add("answer='" + answer + "'")
        .toString();
  }
}
