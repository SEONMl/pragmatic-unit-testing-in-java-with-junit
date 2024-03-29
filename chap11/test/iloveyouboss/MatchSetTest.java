package iloveyouboss;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchSetTest {
    private Criteria criteria;
    private Question questionReimbursesTuition;
    private Answer answerReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;

    private Question questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNoRelocation;

    private Question questionOnsiteDaycare;
    private Answer answerNoOnsiteDaycare;
    private Answer answerHasOnsiteDaycare;

    private AnswerCollection answers;

    @BeforeEach
    public void createAnswers() {
        answers = new AnswerCollection();
    }

    @BeforeEach
    public void createCriteria() {
        criteria = new Criteria();
    }

    @BeforeEach
    public void createQuestionsAndAnswers() {
        questionIsThereRelocation =
                new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation =
                new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNoRelocation =
                new Answer(questionIsThereRelocation, Bool.FALSE);

        questionReimbursesTuition =
                new BooleanQuestion(1, "Reimburses tuition?");
        answerReimbursesTuition =
                new Answer(questionReimbursesTuition, Bool.TRUE);
        answerDoesNotReimburseTuition =
                new Answer(questionReimbursesTuition, Bool.FALSE);

        questionOnsiteDaycare =
                new BooleanQuestion(1, "Onsite daycare?");
        answerHasOnsiteDaycare =
                new Answer(questionOnsiteDaycare, Bool.TRUE);
        answerNoOnsiteDaycare =
                new Answer(questionOnsiteDaycare, Bool.FALSE);
    }

    private MatchSet createMatchSet() {
        return new MatchSet(answers, criteria);
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        answers.add(answerDoesNotReimburseTuition);
        criteria.add(
                new Criterion(answerReimbursesTuition, Weight.MustMatch));

        assertFalse(createMatchSet().matches());
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        answers.add(answerDoesNotReimburseTuition);
        criteria.add(
                new Criterion(answerReimbursesTuition, Weight.DontCare));

        assertTrue(createMatchSet().matches());
    }

    @Test
    public void matchAnswersTrueWhenAnyOfMultipleCriteriaMatch() {
        answers.add(answerThereIsRelocation);
        answers.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        assertTrue(createMatchSet().matches());
    }

    @Test
    public void matchAnswersFalseWhenNoneOfMultipleCriteriaMatch() {
        answers.add(answerThereIsNoRelocation);
        answers.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        assertFalse(createMatchSet().matches());
    }

    @Test
    public void scoreIsZeroWhenThereAreNoMatches() {
        answers.add(answerThereIsNoRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

        assertEquals(createMatchSet().getScore(), 0);
    }

    @Test
    public void scoreIsCriterionValueForSingleMatch() {
        answers.add(answerThereIsRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

        assertEquals(createMatchSet().getScore(), Weight.Important.getValue());
    }

    @Test
    public void scoreAccumulatesCriterionValuesForMatches() {
        answers.add(answerThereIsRelocation);
        answers.add(answerReimbursesTuition);
        answers.add(answerNoOnsiteDaycare);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.WouldPrefer));
        criteria.add(new Criterion(answerHasOnsiteDaycare, Weight.VeryImportant));

        int expectedScore = Weight.Important.getValue() + Weight.WouldPrefer.getValue();
        assertEquals(createMatchSet().getScore(), expectedScore);
    }

    // TODO: missing functionality--what if there is no matching profile answer for a criterion?
}