package com.example.chatbotbackend.service;

import com.example.chatbotbackend.opennlpServicesFiles.OpenNLPFiles;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;

import java.io.*;

public class LanguageServiceImpl implements LanguageService {

  @Override
  public String detectLanguage(String sentence) throws IOException {

    try (InputStream model = new FileInputStream(OpenNLPFiles.LANGUAGE_DETECTOR)) {
      LanguageDetectorModel trainModel = new LanguageDetectorModel(model);
      LanguageDetector languageDetector = new LanguageDetectorME(trainModel);

      return languageDetector.predictLanguage(sentence).getLang();
    }
  }

  public static void main(String[] args) throws IOException {
    LanguageService languageService = new LanguageServiceImpl();
  }
}
