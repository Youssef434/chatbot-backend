package com.example.chatbotbackend;

import com.example.chatbotbackend.service.nlp.LanguageService;
import com.example.chatbotbackend.service.nlp.LanguageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageServiceTest {
  private LanguageService languageService;

  @BeforeEach
  public void init() {
    this.languageService = new LanguageServiceImpl();
  }

  @Test
  public void testDetectFrench() throws IOException {
    assertEquals("fra", languageService.detectLanguage("Bonjour Tout le monde"));
    assertEquals("fra", languageService.detectLanguage("Comment allez-vous ?"));
    assertEquals("fra", languageService.detectLanguage("Quels sont les services offert ?"));
    assertEquals("fra", languageService.detectLanguage("Quels sont les frais d'inscription ?"));
  }

  @Test
  public void testDetectEnglish() throws IOException {
    assertEquals("eng", languageService.detectLanguage("Hello World!"));
    assertEquals("eng", languageService.detectLanguage("How are you doing ?"));
    assertEquals("eng", languageService.detectLanguage("I have a question for you."));
    assertEquals("eng", languageService.detectLanguage("See you soon, take care."));
  }

  @Test
  public void testLangsFullName() throws IOException {
    String[] langsAbbr = {"eng", "fra", "deu", "ces", "dan"};
    String[] langsFullName = {"English", "French", "German", "Czech", "Danish"};

    for (int i = 0; i < langsAbbr.length; i++) {
      assertEquals(langsFullName[i], languageService.getLanguageFullName(langsAbbr[i]));
    }
  }

}
