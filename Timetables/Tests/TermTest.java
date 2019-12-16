import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.*;

public class TermTest {
    @Test
    public void toStringTest() throws Exception {
        Term termin = new Term(9, 45, Day.MON);
        assertEquals("9:45 [90]", termin.toString());
    }

    @Test
    public void earlierThanTest() throws Exception {
        Term termin1 = new Term(9, 45, Day.MON);
        Term termin2 = new Term(10, 20, Day.MON);
        assertTrue(termin1.earlierThan(termin2));
    }

    @Test
    public void laterThanTest() throws Exception {
        Term termin1 = new Term(9, 45, Day.MON);
        Term termin2 = new Term(10, 20, Day.MON);
        assertFalse(termin1.laterThan(termin2));
    }

    @Test
    public void endTermParametersTest() throws Exception {
        Term termin1 = new Term(9, 45, Day.MON);
        Term termin2 = new Term(10, 15, Day.MON);
        assertEquals("9:45 [30]", termin1.endTerm(termin2).toString());
    }

    @Test
    public void endTermNoParametersTest() throws Exception {
        Term termin = new Term(9, 45, Day.MON);
        assertEquals("11:15 [90]", termin.endTerm().toString());
    }

    @Test
    public void testEquals() throws Exception {
        Term term1 = new Term(9, 45, Day.MON);
        Term term2 = new Term(9, 45, Day.MON);
        Term term3 = new Term(9, 45, Day.MON, 60);
        Term term4 = new Term(9, 45, Day.FRI);
        assertTrue(term1.equals(term2));
        assertFalse(term1.equals(term3));
        assertFalse(term1.equals(term4));
    }

    @Test
    public void overlap() {
        Term term = new Term(10, 30, Day.MON);
        Term term2 = new Term(11, 50, Day.MON);
        Term term3 = new Term(12, 00, Day.MON);
        Term term4 = new Term(11, 50, Day.TUE);
        assertTrue(term.overlap(term2, true));
        assertFalse(term.overlap(term3, true));
        assertFalse(term.overlap(term4, false));
    }
}