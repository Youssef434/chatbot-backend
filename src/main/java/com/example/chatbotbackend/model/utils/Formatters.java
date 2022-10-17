package com.example.chatbotbackend.model.utils;

public class Formatters {
  public static String replaceSpaces(String str) {
    return str.strip().replaceAll(" ", "-");
  }
}
