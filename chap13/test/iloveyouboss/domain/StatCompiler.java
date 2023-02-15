package iloveyouboss.domain;

import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.atomic.*;

import iloveyouboss.BooleanQuestion;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatCompilerTest {

    @Mock
    private StatCompiler.QuestionController controller;
    @InjectMock
    private StatCompiler stats;

    @BeforeEach
    public void initalize() {
        stats=new StatCompiler();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        StatCompiler stats = new StatCompiler();

        List<BooleanAnswer> answers = new ArrayList<>();
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, false));
        answers.add(new BooleanAnswer(2, true));
        answers.add(new BooleanAnswer(2, true));

        Map<String, Map<Boolean,AtomicInteger>> responses = stats.responsesByQuestion(answers);

        assertEquals(responses.get("Tuition reimbursement?").get(Boolean.TRUE).get(), (3));
        assertEquals(responses.get("Tuition reimbursement?").get(Boolean.FALSE).get(), (1));
        assertEquals(responses.get("Relocation package?").get(Boolean.TRUE).get(), (2));
        assertEquals(responses.get("Relocation package?").get(Boolean.FALSE).get(), (0));
    }

    @Test
    public void questionTextDoesStuff() {
        when(controller.find(1)).thenReturn(new BooleanQuestion("text1"));
        when(controller.find(2)).thenReturn(new BooleanQuestion("text2"));
        List<BooleanAnswer> answers = new ArrayList<>();
        answers.add(new BooleanAnswer(1,true));
        answers.add(new BooleanAnswer(2,true));

        Map<Integer, String> questionText = stats.questionTest(answers);

        Map<Integer,String> expected = new HashMap<>();
        expected.put(1,"text1");
        expected.put(2, "text2");
        assertEquals(questionText, expected);


    }
}