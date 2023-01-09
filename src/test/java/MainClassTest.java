import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        int actualValue = MainClass.getLocalNumber();
        int expectedValue = 14;
        Assert.assertEquals(String.format("Значения не совпадают! Ожидали %s, а получили %s", expectedValue, actualValue),
                expectedValue, actualValue);
    }

}
