package com.example.chatbotbackend.service.nlp;

import java.io.IOException;

public interface TokenizeService {
  String[] breakSentence(String sentence) throws IOException;
  String[] tokenize(String sentence) throws IOException;
}
