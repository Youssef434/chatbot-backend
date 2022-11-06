package com.example.chatbotbackend.service.nlp.model;

import opennlp.tools.doccat.DoccatModel;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.chatbotbackend.model.QA;

@Service
public interface ModelService {
  List<QA> loadQuestions(String langName);
  String[] decomposeToSentences(String sentence);
  String[] decomposeToWords(String sentence);
  DoccatModel trainModel();
  String detectCategory(DoccatModel model, String[] tokens);
}
