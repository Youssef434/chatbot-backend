package com.example.chatbotbackend.service.nlp.model;

import com.example.chatbotbackend.opennlpServicesFiles.FilePaths;
import com.example.chatbotbackend.repository.QARepository;
import com.example.chatbotbackend.service.nlp.POSService;
import com.example.chatbotbackend.service.nlp.TokenizeService;
import opennlp.tools.doccat.*;
import opennlp.tools.util.*;
import opennlp.tools.util.model.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class Model {
  private QARepository qaRepository;

  private TokenizeService tokenizeService;

//  private final POSService posService;


  public Model(QARepository qaRepository, TokenizeService tokenizeService) {
    this.qaRepository = qaRepository;
    this.tokenizeService = tokenizeService;
  }

  public static final class QAEntity {
    private final String question;
    private final String answer;

    QAEntity(String question, String answer) {
      this.question = question;
      this.answer = answer;
    }

    public String getQuestion() {
      return question;
    }
  }

  public List<QAEntity> loadQuestions() {
    return qaRepository.findAll()
        .stream()
        .map(qa -> new Model.QAEntity(qa.getQuestion(), qa.getAnswer(FilePaths.lang)))
        .toList();
  }

  public String[] decomposeToSentences(String sentence) throws IOException {
    return tokenizeService.breakSentence(sentence);
  }

  public String[] decomposeToWords(String sentence) throws IOException {
    return tokenizeService.tokenize(sentence);
  }

  public DoccatModel trainModel() throws IOException {
    InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File(FilePaths.CATEGORIES_PATH));

    try (
        ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);
        ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream)
    ) {
      DoccatFactory doccatFactory = new DoccatFactory(new FeatureGenerator[] { new BagOfWordsFeatureGenerator() });
      TrainingParameters trainingParameters = ModelUtil.createDefaultTrainingParameters();
      trainingParameters.put(TrainingParameters.CUTOFF_PARAM, 0);
      return DocumentCategorizerME.train(FilePaths.lang.substring(0, 2), sampleStream, trainingParameters, doccatFactory);
    }
  }

  public String detectCategory(DoccatModel model, String[] tokens) {
    DocumentCategorizerME documentCategorizerME = new DocumentCategorizerME(model);
    double[] outcomes = documentCategorizerME.categorize(tokens);
    return documentCategorizerME.getBestCategory(outcomes);
  }
}
