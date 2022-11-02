package com.example.chatbotbackend.model;

import java.time.LocalDateTime;

public final class CustomResponse {
  private final String response;
  private final LocalDateTime timestamp;

  public CustomResponse(String response) {
    this.response = response;
    this.timestamp = LocalDateTime.now();
  }
}
