package com.example.chatbotbackend.service.chatbot;

import com.example.chatbotbackend.model.Conversation;
import com.example.chatbotbackend.service.chatbot.utils.Formatters;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChatBot implements ChatBotOps {
  Map<String, String> questionAnswersMockRepo;
  Conversation conversation = new Conversation();

  public ChatBot() {
    questionAnswersMockRepo = loadQuestionsAndAnswers();
  }

  private Map<String, String> loadQuestionsAndAnswers() {
    return new HashMap<>();
  }

  public ChatBot(Map<String, String> questionAnswersMockRepo) {
    this.questionAnswersMockRepo = questionAnswersMockRepo;
  }

  @Override
  public String respond(String question) {
    return questionAnswersMockRepo.getOrDefault(question, "Sorry I didn't understand! Can you ask me another question ?");
  }

  public void start() {
    System.out.println("Hello");
    System.out.println("I'm your assistant");
    System.out.println("What can I help you with !!");
    System.out.println();
    receiveQuestions();
  }

  private void receiveQuestions() {
    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        String question = Formatters.replaceSpaces(scanner.next());
        if (question.equals("exit")) {
//          conversation.persist();
          leaveTheConversation();
          return;
        }

        String response = respond(question);
//        conversation.logQA(question, response);
        System.out.println(response);
      }
    }
  }

  private void leaveTheConversation() {
    System.out.println();
    System.out.println("I was very fun helping you !!");
    System.out.println("See you later ...");
  }
}
