package com.example.chatbotbackend.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public interface LanguageService {
  String detectLanguage(String sentence) throws IOException;
}
