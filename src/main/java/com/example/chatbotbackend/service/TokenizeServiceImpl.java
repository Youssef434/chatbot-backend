package com.example.chatbotbackend.service;

import static com.example.chatbotbackend.opennlpServicesFiles.OpenNLPFiles.LANGUAGE_TOKENIZER;
import static com.example.chatbotbackend.opennlpServicesFiles.OpenNLPFiles.LANGUAGE_SENT;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class TokenizeServiceImpl implements TokenizeService {
  @Override
  public String[] breakSentence(String sentence) throws IOException {
    try (InputStream inputStream = new FileInputStream(LANGUAGE_SENT)) {
      SentenceModel model = new SentenceModel(inputStream);
      SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);

      return sentenceDetector.sentDetect(sentence);
    }
  }

  @Override
  public String[] tokenize(String sentence) throws IOException {
    try (InputStream inputStream = new FileInputStream(LANGUAGE_TOKENIZER)) {
      TokenizerModel model = new TokenizerModel(inputStream);
      TokenizerME tokenizer = new TokenizerME(model);

      return tokenizer.tokenize(sentence);
    }
  }

  public static void main(String[] args) throws IOException {
    TokenizeService tokenizeService = new TokenizeServiceImpl();
    System.out.println(Arrays.toString(tokenizeService.tokenize("Hello, World!")));
    System.out.println(Arrays.toString(tokenizeService.breakSentence("Hello, World!")));
  }


}
