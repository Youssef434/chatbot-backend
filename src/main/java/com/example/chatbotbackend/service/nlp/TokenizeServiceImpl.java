package com.example.chatbotbackend.service.nlp;

import com.example.chatbotbackend.opennlpServicesFiles.FilePaths;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class TokenizeServiceImpl implements TokenizeService {
  @Override
  public String[] breakSentence(String sentence) throws IOException {
    try (InputStream inputStream = new FileInputStream(FilePaths.getLanguageSent())) {
      SentenceModel model = new SentenceModel(inputStream);
      SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);

      return sentenceDetector.sentDetect(sentence);
    }
  }

  @Override
  public String[] tokenize(String sentence) throws IOException {
    try (InputStream inputStream = new FileInputStream(FilePaths.getLanguageTokenizer())) {
      TokenizerModel model = new TokenizerModel(inputStream);
      TokenizerME tokenizer = new TokenizerME(model);

      return tokenizer.tokenize(sentence);
    }
  }
}
