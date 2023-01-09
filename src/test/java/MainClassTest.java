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

    @Test
    public void testGetClassNumber() {
        int boundaryValue = 45;
        int actualValue = MainClass.getClassNumber();
        Assert.assertTrue(String.format("Значение метода getClassNumber() %s меньше или равно граничной величины %s", actualValue, boundaryValue),
                actualValue > boundaryValue);
    }

    @Test
    public void testGetClassString() {
        String originalValue = MainClass.getClassString();
        String subString1 = "hello";
        String subString2 = "Hello";
        Assert.assertTrue(String.format("Строка %s не содержит подстроку %s или %s", originalValue, subString1, subString2),
                originalValue.contains(subString1) || originalValue.contains(subString2));
    }

}
