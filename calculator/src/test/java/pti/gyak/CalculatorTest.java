package pti.gyak;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {
    @Test
    public void testResult() {
        assertEquals(6.0, (new Calculator()).enter(5).enter(Operation.ADD).enter(1).result);
    }

    @Test
    public void testSubtract() {
        assertEquals(4.0, (new Calculator()).enter(5).enter(Operation.SUBSTRACT).enter(1).result);
    }

    @Test
    public void testMultiply() {
        assertEquals(10.0, (new Calculator()).enter(5).enter(Operation.MULTIPLY).enter(2).result);
    }

    @Test
    public void testDivideFirst() {
        assertEquals(5.0, (new Calculator()).enter(10).enter(Operation.DIVIDE).enter(2).result);
    }

    @Test
    public void testDivideSec() {
        assertEquals(Double.POSITIVE_INFINITY, (new Calculator()).enter(10).enter(Operation.DIVIDE).enter(0).result);
    }

    @Test
    public void testReminder() {
        assertEquals(1.0, (new Calculator()).enter(10).enter(Operation.REMAINDER).enter(3).result);
    }

    @Test
    public void testPow() {
        assertEquals(4.0, (new Calculator()).enter(2).enter(Operation.POWER).enter(2).result);
    }

    @Test
    public void testEnterClear() {
        assertEquals(0, (new Calculator()).enter(Operation.CLEAR).result);
    }


    @Test
    public void testHistory() {
        assertEquals("5 + 1 = 6", (new Calculator()).enter(5).enter(Operation.ADD).enter(1).getHistory());
    }
}