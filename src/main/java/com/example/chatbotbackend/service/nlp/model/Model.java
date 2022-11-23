package com.example.chatbotbackend.service.nlp.model;

import com.example.chatbotbackend.opennlpServicesFiles.FilePaths;
import com.example.chatbotbackend.repository.QARepository;
import com.example.chatbotbackend.service.nlp.POSService;
import com.example.chatbotbackend.service.nlp.TokenizeService;
import opennlp.tools.doccat.*;
import opennlp.tools.util.*;
import opennlp.tools.util.model.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;


public class Model {
  private QARepository qaRepository;

  private TokenizeService tokenizeService;


  public Model(QARepository qaRepository, TokenizeService tokenizeService) {
    this.qaRepository = qaRepository;
    this.tokenizeService = tokenizeService;
  }

  public static final class QAEntity {
    private final String question;
    private final String answer;

    public QAEntity(String question, String answer) {
      this.question = question;
      this.answer = answer;
    }

    public String getQuestion() {
      return question;
    }

    public String getAnswer() {
      return answer;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", QAEntity.class.getSimpleName() + "[", "]")
          .add("question='" + question + "'")
          .add("answer='" + answer + "'")
          .toString();
    }
  }

  public List<QAEntity> loadQuestions() {
    return qaRepository.findAll()
        .stream()
        .map(qa -> new Model.QAEntity(qa.getQuestion(), qa.getAnswer(FilePaths.lang.substring(0, 2))))
        .toList();
  }

  public String[] decomposeToSentences(String sentence) throws IOException {
    return tokenizeService.breakSentence(sentence);
  }

  public String[] decomposeToWords(String sentence) throws IOException {
    return tokenizeService.tokenize(sentence);
  }

  public DoccatModel trainModel() throws IOException {
    InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File(FilePaths.getCategoriesPath()));

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
    System.out.println(Arrays.toString(outcomes));
    return documentCategorizerME.getBestCategory(outcomes);
  }
}
