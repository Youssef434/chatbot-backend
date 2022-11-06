package com.example.chatbotbackend.service.nlp.model;

import com.example.chatbotbackend.model.QA;
import com.example.chatbotbackend.repository.QARepository;
import opennlp.tools.doccat.DoccatModel;

import java.util.List;

public class ModelImpl implements ModelService {
  private final QARepository qaRepository;

  public ModelImpl(QARepository qaRepository) {
    this.qaRepository = qaRepository;
  }

  @Override
  public List<QA> loadQuestions(String langName) {
    return qaRepository.findAll();
  }

  @Override
  public String[] decomposeToSentences(String sentence) {
    return new String[0];
  }

  @Override
  public String[] decomposeToWords(String sentence) {
    return new String[0];
  }

  @Override
  public DoccatModel trainModel() {
    return null;
  }

  @Override
  public String detectCategory(DoccatModel model, String[] tokens) {
    return null;
  }
}
