import java.util.ArrayList;
import java.util.List;

public class Conversation {

  private static final class QA {
    private final String question;
    private final String answer;

    public QA(String question, String answer) {
      this.question = question;
      this.answer = answer;
    }
  }
  private List<QA> questionsAnswers;

  public Conversation() {
    questionsAnswers = new ArrayList<>();
  }

  public void logQA(String question, String answer) {
    questionsAnswers.add(new QA(question, answer));
  }

  public void persist() {

  }


}
