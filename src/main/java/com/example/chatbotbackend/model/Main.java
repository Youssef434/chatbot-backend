import opennlp.tools.cmdline.tokenizer.TokenizerMETool;

import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    Map<String, String> qr = new HashMap<>();
    qr.put("What's-the-name-of-the-service", "XYZ");
    qr.put("What-are-the-categories", "One Two Three");

    ChatBot chatBot = new ChatBot(qr);

    chatBot.start();

  }
}
