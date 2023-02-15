package iloveyouboss;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerCollectionTest {
    private AnswerCollection answers;

    @BeforeEach
    public void createProfile() {
        answers = new AnswerCollection();
    }

    int[] ids(Collection<Answer> answers) {
        return answers.stream()
                .mapToInt(a -> a.getQuestion().getId()).toArray();
    }

    @Test
    public void findsAnswersBasedOnPredicate() {
        answers.add(new Answer(new BooleanQuestion(1, "1"), Bool.FALSE));
        answers.add(new Answer(new PercentileQuestion(2, "2", new String[] {}), 0));
        answers.add(new Answer(new PercentileQuestion(3, "3", new String[] {}), 0));

        List<Answer> result =
                answers.find(a -> a.getQuestion().getClass() == PercentileQuestion.class);

        assertEquals(ids(result), new int[] { 2, 3 });
    }
}