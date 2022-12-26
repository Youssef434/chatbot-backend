package com.example.chatbotbackend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumAutomatedTest {
  private WebDriver driver;

  @BeforeEach
  public void init() {
    System.setProperty("webdriver.chrome.driver", "chromedriver");
    driver = new ChromeDriver();
    System.out.println(driver);
  }

  @Test
  public void navigateToGoogle() {
    driver.get("https://www.google.com");
    driver.manage().window().fullscreen();
  }
}
