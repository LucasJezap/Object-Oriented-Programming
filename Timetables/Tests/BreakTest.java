import org.junit.Test;

import static org.junit.Assert.*;

public class BreakTest {

    @Test
    public void testToString() {
        Break fBreak = new Break(new Term(10, 30, Day.TUE, 60));
        Break sBreak = new Break(new Term(10, 30, Day.TUE));
        assertEquals("Przerwa", fBreak.toString());
        assertEquals("Przerwa", sBreak.toString());
    }

    @Test
    public void getTerm() {
        Break fBreak = new Break(new Term(10, 30, Day.TUE, 60));
        Break sBreak = new Break(new Term(10, 30, Day.TUE));
        assertEquals("Przerwa", fBreak.toString());
        assertEquals("Przerwa", sBreak.toString());
    }

    @Test
    public void getActualTerm() {
        Break fBreak = new Break(new Term(10, 30, Day.TUE, 60));
        Break sBreak = new Break(new Term(10, 30, Day.TUE));
        assertTrue(fBreak.getActualTerm().equals(new Term(10, 30, Day.TUE, 60)));
        assertTrue(sBreak.getActualTerm().equals(new Term(10, 30, Day.TUE)));
    }

    @Test
    public void setTerm() {
        Break fBreak = new Break(new Term(10, 30, Day.TUE, 60));
        Break sBreak = new Break(new Term(10, 30, Day.TUE));
        fBreak.setTerm(new Term(12, 30, Day.WED, 60));
        sBreak.setTerm(new Term(12, 30, Day.FRI));
        assertTrue(fBreak.getActualTerm().equals(new Term(12, 30, Day.WED, 60)));
        assertTrue(sBreak.getActualTerm().equals(new Term(12, 30, Day.FRI)));
    }
}