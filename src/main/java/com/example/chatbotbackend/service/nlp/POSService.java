package com.example.chatbotbackend.service.nlp;

public interface POSService {
  String[] detectPOSTags(String[] tokens);
}
