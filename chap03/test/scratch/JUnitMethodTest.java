package scratch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.InsufficientResourcesException;

import java.beans.FeatureDescriptor;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.BufferUnderflowException;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void depositIncreaseBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() > initialBalance);
    }

    @Test
    public void assertThatTest() {
        account.deposit(100);
        assertEquals(100, account.getBalance());
    }

    // JUnit4
//    @Test(expected= InsufficientResourcesException.class)
//    public void throwsWhenWithdrawingTooMuch() {
//        account.withdraw(100);
//    }

    //JUnit5
    @Test
    public void throwsWhenWithdrawingTooMuch() {
        assertThrows(IllegalArgumentException.class, ()->{
            account.withdraw(100);
        });
    }

    @Test
    public void readsFromTestFile() throws Exception {
        String filename = "test.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("test data");
        writer.close();
    }

}
