import org.junit.Test;

import static org.junit.Assert.*;

public class BasicTermTest {

    @Test
    public void fullHours() {
        BasicTerm term = new BasicTerm(10, 30, 90);
        assertEquals("10:30-12:00", term.fullHours());
    }

    @Test
    public void toStringHour() {
        BasicTerm term = new BasicTerm(10, 30, 90);
        assertEquals("10:30", term.toStringHour(term));
    }

    @Test
    public void testToString() {
        BasicTerm term = new BasicTerm(10, 30, 90);
        assertEquals("10:30 [90]", term.toString());
    }

    @Test
    public void earlierThan() {
        BasicTerm term = new BasicTerm(10, 30, 90);
        BasicTerm term2 = new BasicTerm(12, 00, 90);
        BasicTerm term3 = new BasicTerm(10, 29, 90);
        assertTrue(term.earlierThan(term2));
        assertFalse(term.earlierThan(term3));
    }

    @Test
    public void laterThan() {
        BasicTerm term = new BasicTerm(10, 30, 90);
        BasicTerm term2 = new BasicTerm(12, 00, 90);
        BasicTerm term3 = new BasicTerm(10, 29, 90);
        assertFalse(term.laterThan(term2));
        assertTrue(term.laterThan(term3));
    }

    @Test
    public void endTerm() {
        BasicTerm term = new BasicTerm(10, 30, 90);
        BasicTerm term2 = new BasicTerm(11, 30, 90);
        assertEquals("10:30 [60]", term.endTerm(term2).toString());
    }

    @Test
    public void testEndTerm() {
        BasicTerm term = new BasicTerm(10, 30, 90);
        assertEquals("12:00 [90]", term.endTerm().toString());
    }

    @Test
    public void testEquals() {
        BasicTerm term = new BasicTerm(10, 30, 90);
        BasicTerm term2 = new BasicTerm(10, 30, 90);
        BasicTerm term3 = new BasicTerm(10, 30, 80);
        assertTrue(term.equals(term2));
        assertFalse(term.equals(term3));
    }

    @Test
    public void toMinutes() {
        BasicTerm term = new BasicTerm(10, 30, 90);
        assertEquals(630, term.toMinutes());
    }

    @Test
    public void overlap() {
        BasicTerm term = new BasicTerm(10, 30, 90);
        BasicTerm term2 = new BasicTerm(11, 50, 90);
        BasicTerm term3 = new BasicTerm(12, 00, 90);
        assertTrue(term.overlap(term2, true));
        assertFalse(term.overlap(term3, true));
    }
}