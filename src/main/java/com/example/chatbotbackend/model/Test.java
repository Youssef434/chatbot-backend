package com.example.chatbotbackend.model;

import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.*;
import java.util.Arrays;

public class Test {
  public String[] detectSentences(String paragraph) throws IOException {
    String[] sentences;

    try (InputStream modelInput = new FileInputStream("src/en-sent.bin")) {
      SentenceModel sentenceModel = new SentenceModel(modelInput);
      SentenceDetector sentenceDetector = new SentenceDetectorME(sentenceModel);
      sentences = sentenceDetector.sentDetect(paragraph);
    }

    return sentences;
  }

  public String[] tokenize(String input) throws IOException {
    TokenizerModel model = new TokenizerModel(new File("src/en-token.bin"));

    return new TokenizerME(model).tokenize(input);
  }

  public String langDetector(String input) throws IOException {
    LanguageDetector detector = new LanguageDetectorME(new LanguageDetectorModel(new File("src/langdetect-183.bin")));
    Language theLanguage = detector.predictLanguage(input);

    return theLanguage.getLang();
  }

  public static void main(String[] args) throws IOException {
    String paragraph = "This is a statement. This is another statement. Now is an abstract word for time, that is always flying.";
    Test test = new Test();
    System.out.println(Arrays.toString(test.detectSentences(paragraph)));
    System.out.println(Arrays.toString(test.tokenize(paragraph)));
    System.out.println(test.langDetector(paragraph));
  }
}
