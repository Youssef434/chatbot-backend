package com.example.chatbotbackend.service.nlp;

import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import static com.example.chatbotbackend.opennlpServicesFiles.FilePaths.LANG_NAMES;
import static com.example.chatbotbackend.opennlpServicesFiles.FilePaths.LANGUAGE_DETECTOR;

public class LanguageServiceImpl implements LanguageService {
  Map<String, String> langNames;

  @Override
  public String detectLanguage(String sentence) throws IOException {

    try (InputStream model = new FileInputStream(LANGUAGE_DETECTOR)) {
      LanguageDetectorModel trainModel = new LanguageDetectorModel(model);
      LanguageDetector languageDetector = new LanguageDetectorME(trainModel);

      return languageDetector.predictLanguage(sentence).getLang();
    }
  }

  @Override
  public String getLanguageFullName(String abbr) throws IOException {
    if (langNames == null) {
      loadLangNames();
    }

    return langNames.getOrDefault(abbr, "the language is unknown.");
  }

  private void loadLangNames() throws IOException {
    langNames = new HashMap<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(LANG_NAMES))) {
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        String[] splitLine = line.split("\t");

        langNames.put(splitLine[0], splitLine[1]);
      }
    }
  }

//  public static void main(String[] args) throws IOException {
//    LanguageService languageService = new LanguageServiceImpl();
//    System.out.println(languageService.getLanguageFullName("fra"));
//  }
}
