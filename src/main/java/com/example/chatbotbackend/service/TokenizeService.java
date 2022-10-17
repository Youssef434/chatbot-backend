package com.example.chatbotbackend.service;

import java.io.IOException;

public interface TokenizeService {
  String[] breakSentence(String sentence) throws IOException;
  String[] tokenize(String sentence) throws IOException;
}
