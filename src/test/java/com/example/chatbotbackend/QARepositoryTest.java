package com.example.chatbotbackend;

import com.example.chatbotbackend.model.QA;
import com.example.chatbotbackend.repository.QARepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class QARepositoryTest {
  @Autowired
  private QARepository qaRepository;

  @Test
  public void testDBShouldNotBeEmpty() {
    Assertions.assertFalse(qaRepository.findAll().isEmpty());
  }

  @Test
  public void testDBShouldHaveFourCategories() {
    List<String> categories = qaRepository.findAll()
        .stream()
        .map(QA::getQuestion)
        .toList();

    assertThat(categories).hasSameElementsAs(List.of("unknown", "greeting", "services", "costs"));
  }

  @Test
  public void testDropDBElements() {
    List<QA> qaList = qaRepository.findAll();
    qaRepository.deleteAll();

    assertThat(qaRepository.findAll()).isEmpty();
    qaRepository.saveAll(qaList);
  }

  @Test
  public void testAddLanguageQAElements() {
    String langName = "ar";
    List<String> listAnswers = List.of("AAAAAAAAAAAAAAAA", "BBBBBBBBBBBBBBB", "CCCCCCCCCCCCCC", "DDDDDDDDDDDDDD");
    List<QA> qaBackUpList = qaRepository.findAll();
    List<QA> qaList = qaRepository.findAll();
    qaRepository.deleteAll();

    for (int i = 0; i < qaList.size(); i++) {
      qaList.get(i).addAnswer(langName, listAnswers.get(i));
    }
    qaList = qaRepository.saveAll(qaList);

    var answersSet = qaList.stream().map(QA::getAnswersSet).collect(Collectors.toSet());

    for (var element : answersSet) {
      assertThat(element)
          .hasSize(3);
    }

    qaRepository.deleteAll();
    qaRepository.saveAll(qaBackUpList);
  }
}
