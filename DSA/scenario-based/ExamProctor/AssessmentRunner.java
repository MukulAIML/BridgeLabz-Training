public class AssessmentRunner {

    public static void main(String[] args) {

        TestSessionManager session = new TestSessionManager();

        session.openQuestion(101);
        session.openQuestion(102);
        session.openQuestion(103);
        session.displayNavigation();

        session.defineAnswerKey(101, "A");
        session.defineAnswerKey(102, "B");
        session.defineAnswerKey(103, "C");

        session.saveResponse(101, "A");
        session.saveResponse(102, "C");
        session.saveResponse(103, "C");

        EvaluationStrategy evaluator = (responses, correct) -> {
            int total = 0;

            for (Integer id : responses.keySet()) {
                String given = responses.get(id);
                String expected = correct.get(id);

                if (expected != null && given != null &&
                        expected.equalsIgnoreCase(given)) {
                    total++;
                }
            }
            return total;
        };

        int finalScore = session.finalizeTest(evaluator);
        System.out.println("Final Score: " + finalScore);
    }
}

