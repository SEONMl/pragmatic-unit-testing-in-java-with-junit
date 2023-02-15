package iloveyouboss.controller;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import iloveyouboss.Question;
import iloveyouboss.domain.*;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuestionControllerTest {

    private QuestionController controller;
    @BeforeEach
    public void create() {
        controller = new QuestionController();
        controller.deleteAll();
    }


    @AfterEach
    public void cleanup() {
        controller.deleteAll();
    }

    @Test
    public void findsPersistedQuestionById() {
        int id = controller.addBooleanQuestion("question text");

        Question question = controller.find(id);

        assertEquals(question.getText(), ("question text"));
    }

    @Test
    public void questionAnswersDateAdded() {
        Instant now = new Date().toInstant();
        controller.setClock(Clock.fixed(now, ZoneId.of("America/Denver")));
        int id = controller.addBooleanQuestion("text");

        Question question = controller.find(id);

        assertEquals(question.getCreateTimestamp(), (now));
    }

    @Test
    public void answersMultiplePersistedQuestions() {
        controller.addBooleanQuestion("q1");
        controller.addBooleanQuestion("q2");
        controller.addPercentileQuestion("q3", new String[] { "a1", "a2"});

        List<Question> questions = controller.getAll();

        assertEquals(
                questions.stream().map(Question::getText).collect(Collectors.toList()),
                (Arrays.asList("q1", "q2", "q3")));
    }

    @Test
    public void findsMatchingEntries() {
        controller.addBooleanQuestion("alpha 1");
        controller.addBooleanQuestion("alpha 2");
        controller.addBooleanQuestion("beta 1");

        List<Question> questions = controller.findWithMatchingText("alpha");

        assertEquals(
                questions.stream().map(Question::getText).collect(Collectors.toList()),
                (Arrays.asList("alpha 1", "alpha 2")));
    }
}