package com.example.chatbotbackend.service.nlp;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface TokenizeService {
  String[] breakSentence(String sentence) throws IOException;
  String[] tokenize(String sentence) throws IOException;
}
