import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TestSessionManager {
    private Map<Integer, String> answerKey = new HashMap<>();
    private Map<Integer, String> responses = new TreeMap<>();
    private Map<Integer, String> questions = new TreeMap<>();

    public void openQuestion(int id) {
        questions.put(id, "Question " + id);
    }

    public void displayNavigation() {
        System.out.println("Available Questions:");
        for (Integer id : questions.keySet()) {
            System.out.println("- " + questions.get(id));
        }
    }

    public void defineAnswerKey(int id, String answer) {
        answerKey.put(id, answer);
    }

    public void saveResponse(int id, String answer) {
        responses.put(id, answer);
        System.out.println("Response saved for Question " + id + ": " + answer);
    }

    public int finalizeTest(EvaluationStrategy evaluator) {
        return evaluator.evaluate(responses, answerKey);
    }
}

