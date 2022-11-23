package com.example.chatbotbackend.service.nlp;

import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public interface LanguageService {
  String detectLanguage(String sentence) throws IOException;
  String getLanguageFullName(String abbr) throws IOException;
}
