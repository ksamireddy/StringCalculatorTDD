import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void testEmptyString() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void testSingleNumber() {
        assertEquals(5, StringCalculator.add("5"));
    }

    @Test
    public void testTwoNumbers() {
        assertEquals(8, StringCalculator.add("3,5"));
    }

    @Test
    public void testMultipleNumbers() {
        assertEquals(15, StringCalculator.add("1,2,3,4,5"));
    }

    @Test
    public void testNewlineDelimiter() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    public void testCustomDelimiter() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }

    @Test
    public void testNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.add("1,-2,3,-4"));
    }

    @Test
    public void testNumbersGreaterThan1000() {
        assertEquals(5, StringCalculator.add("2,1001,3"));
    }
}