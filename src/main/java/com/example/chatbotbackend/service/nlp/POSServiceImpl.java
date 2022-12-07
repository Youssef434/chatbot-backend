package com.example.chatbotbackend.service.nlp;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.chatbotbackend.opennlpServicesFiles.FilePaths.POS_PATH;

public class POSServiceImpl implements POSService {
  @Override
  public String[] detectPOSTags(String[] tokens) {
    try (InputStream model = new FileInputStream(POS_PATH)) {
      POSTaggerME categorize = new POSTaggerME(new POSModel(model));
      return categorize.tag(tokens);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
