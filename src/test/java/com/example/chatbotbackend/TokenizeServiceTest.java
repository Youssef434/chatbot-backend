package com.example.chatbotbackend;

import com.example.chatbotbackend.service.nlp.TokenizeService;
import com.example.chatbotbackend.service.nlp.TokenizeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TokenizeServiceTest {
  private TokenizeService tokenizeService;

  @BeforeEach
  public void init() {
    tokenizeService = new TokenizeServiceImpl();
  }

  @Test
  public void testBreakSentenceOneWord() throws IOException {
    Assertions.assertArrayEquals(new String[] {"Hello"}, tokenizeService.breakSentence("Hello"));
  }

  @Test
  public void testBreakSentenceMoreThanOneWord() throws IOException {
    Assertions.assertArrayEquals(
        new String[] {"Hello World!", "I am Youssef"},
        tokenizeService.breakSentence("Hello World! I am Youssef"));
    Assertions.assertArrayEquals(
        new String[] {"Bonjour tout le monde comment allez-vous ?"},
        tokenizeService.breakSentence("Bonjour tout le monde comment allez-vous ?"));
  }

  @Test
  public void testBreakSentenceToWordsNoPunctuation() throws IOException {
    Assertions.assertArrayEquals(
        new String[] {"There", "'s", "no", "way"},
        tokenizeService.tokenize("There's no way"));
  }

  @Test
  public void testBreakSentenceToWordsSpecialChars() throws IOException {
    Assertions.assertArrayEquals(
        new String[] {"There", "'s", "-", "%", "no", "25", "^", "way", "*"},
        tokenizeService.tokenize("There's - % no 25 ^ way *"));
  }
}
