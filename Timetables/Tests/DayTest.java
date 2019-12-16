import org.junit.Test;
import static org.junit.Assert.*;

public class DayTest {
    @Test
    public void testNextDayMON() throws Exception {
        Day day = Day.MON;
        assertEquals(Day.TUE, day.nextDay());
    }
    @Test
    public void testNextDayTUE() throws Exception {
        Day day = Day.TUE;
        assertEquals(Day.WED, day.nextDay());
    }
    @Test
    public void testNextDayWED() throws Exception {
        Day day = Day.WED;
        assertEquals(Day.THU, day.nextDay());
    }
    @Test
    public void testNextDayTHU() throws Exception {
        Day day = Day.THU;
        assertEquals(Day.FRI, day.nextDay());
    }
    @Test
    public void testNextDayFRI() throws Exception {
        Day day = Day.FRI;
        assertEquals(Day.SAT, day.nextDay());
    }
    @Test
    public void testNextDaySAT() throws Exception {
        Day day = Day.SAT;
        assertEquals(Day.SUN, day.nextDay());
    }
    @Test
    public void testNextDaySUN() throws Exception {
        Day day = Day.SUN;
        assertEquals(Day.MON, day.nextDay());
    }
    @Test
    public void testPrevDayMON() throws Exception {
        Day day = Day.MON;
        assertEquals(Day.SUN, day.prevDay());
    }
    @Test
    public void testPrevDayTUE() throws Exception {
        Day day = Day.TUE;
        assertEquals(Day.MON, day.prevDay());
    }
    @Test
    public void testPrevDayWED() throws Exception {
        Day day = Day.WED;
        assertEquals(Day.TUE, day.prevDay());
    }
    @Test
    public void testPrevDayTHU() throws Exception {
        Day day = Day.THU;
        assertEquals(Day.WED, day.prevDay());
    }
    @Test
    public void testPrevDayFRI() throws Exception {
        Day day = Day.FRI;
        assertEquals(Day.THU, day.prevDay());
    }
    @Test
    public void testPrevDaySAT() throws Exception {
        Day day = Day.SAT;
        assertEquals(Day.FRI, day.prevDay());
    }
    @Test
    public void testPrevDaySUN() throws Exception {
        Day day = Day.SUN;
        assertEquals(Day.SAT, day.prevDay());
    }
}
