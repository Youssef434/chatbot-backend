package com.example.chatbotbackend.opennlpServicesFiles;

public class FilePaths {
  private static String lang = "en";
  private static final String PATH_FROM_ROOT = "src/main/java/com/example/chatbotbackend/opennlpServicesFiles/";
  public static final String LANGUAGE_DETECTOR = PATH_FROM_ROOT + "langdetect-183.bin";
  public static final String LANGUAGE_SENT = PATH_FROM_ROOT + lang + "-sent.bin";
  public static final String LANGUAGE_TOKENIZER = PATH_FROM_ROOT + lang + "-token.bin";
  public static final String LANG_NAMES = PATH_FROM_ROOT + "lang_names.txt";
  public static final String POS_PATH = PATH_FROM_ROOT + "lang_names.txt";
  public static void changeLanguage(String newLang) {
    lang = newLang;
  }
}
