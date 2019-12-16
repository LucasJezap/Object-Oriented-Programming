import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class Timetable2Test {

    @Test
    public void breakTime() {
        Set<Break> breaks = new TreeSet<Break>();
        breaks.add(new Break(new Term(9, 30, Day.MON, 5)));
        breaks.add(new Break(new Term(11, 5, Day.MON, 10)));
        Timetable2 timetable2 = new Timetable2(breaks, true);
        Timetable2 timetable3 = new Timetable2(breaks, false);
        assertTrue(timetable2.breakTime(new Term(9, 32, Day.TUE)));
        assertTrue(timetable3.breakTime(new Term(9, 32, Day.TUE)));
        assertTrue(timetable2.breakTime(new Term(11, 10, Day.TUE)));
        assertTrue(timetable3.breakTime(new Term(11, 10, Day.TUE)));
        assertFalse(timetable2.breakTime(new Term(9, 35, Day.TUE)));
        assertFalse(timetable2.breakTime(new Term(9, 35, Day.TUE)));
    }

    @Test
    public void put() {
        Set<Break> breaks = new TreeSet<Break>();
        breaks.add(new Break(new Term(9, 30, Day.MON, 5)));
        breaks.add(new Break(new Term(11, 5, Day.MON, 10)));
        Timetable2 timetable2 = new Timetable2(breaks, true);
        Timetable2 timetable3 = new Timetable2(breaks, false);
        assertTrue(timetable2.put(new Lesson(timetable2, new Term(9, 30, Day.MON), "Fizyka wykład", "Kąkol", 2)));
        assertTrue(timetable2.put(new Lesson(timetable2, new Term(11, 5, Day.TUE), "Fizyka wykład", "Kąkol", 2)));
        assertTrue(timetable2.put(new Lesson(timetable2, new Term(17, 0, Day.MON), "Fizyka wykład", "Kąkol", 2)));
        assertFalse(timetable3.put(new Lesson(timetable3, new Term(9, 30, Day.MON), "Fizyka wykład", "Kąkol", 2)));
        assertFalse(timetable3.put(new Lesson(timetable3, new Term(11, 5, Day.TUE), "Fizyka wykład", "Kąkol", 2)));
        assertTrue(timetable3.put(new Lesson(timetable3, new Term(17, 0, Day.MON), "Fizyka wykład", "Kąkol", 2)));
    }

    @Test
    public void testToString() {        // TODO
    }
}