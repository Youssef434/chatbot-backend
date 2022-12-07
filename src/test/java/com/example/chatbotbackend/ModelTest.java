package com.example.chatbotbackend;

import com.example.chatbotbackend.model.QA;
import com.example.chatbotbackend.repository.QARepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

public class ModelTest {
  @Mock
  private QARepository qaRepository;

  @Test
  public void test() {
    Mockito.when(qaRepository.findAll()).thenReturn(List.of(new QA("dw", Map.of("d", "defr"))));
    System.out.println();
  }
}
