@FunctionalInterface
public interface EvaluationStrategy {
    int evaluate(java.util.Map<Integer, String> responses, java.util.Map<Integer, String> correct);
}

