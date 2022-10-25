package com.example.chatbotbackend.service.chatbot.utils;

public class Formatters {
  public static String replaceSpaces(String str) {
    return str.strip().replaceAll(" ", "-");
  }
}
