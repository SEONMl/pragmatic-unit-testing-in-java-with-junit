package scratch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertTest {
    @Test
    public void assertCoreMatchers() {
        // Hamcrest(햄크레스트): assertions와 assumptions를 가독성 있고 편하게 사용할 수 있게 도와주는 라이브러리
        // JUnit4 에 있다가 JUnit5로 오면서 빠진 라이브러리
        // 아래와 같은 사용법
        // assertEquals(new String[] {"1","2"}, equalTo(new String[] {"1","2"}));
    }

    @Test
    public void assertHamcrestTest() {
        assertTrue(Math.abs((2.32 * 3) - 6.96)<0.0005);
        // assertEquals(2.32 * 3, closeTo(6.96, 0.0005));
    }
}
