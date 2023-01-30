package scratch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnitMethodTest {

    private Account account;

    @BeforeEach
    public void createAccount() {
        account = new Account("an account name");
    }

    @Test
    public void hasPositiveBalance(){
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }

}
