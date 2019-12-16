import org.junit.Test;

import static org.junit.Assert.*;

public class Timetable1Test {

    @Test
    public void put() {
        Timetable1 timetable = new Timetable1();
        assertTrue(timetable.put(new Lesson(timetable, new Term(9, 30, Day.MON), "Fizyka wykład", "Kąkol", 2)));
        assertTrue(timetable.put(new Lesson(timetable, new Term(11, 5, Day.TUE), "Fizyka wykład", "Kąkol", 2)));
        assertFalse(timetable.put(new Lesson(timetable, new Term(10, 30, Day.MON), "Fizyka wykład", "Kąkol", 2)));
    }

    @Test
    public void testToString() {         // TODO
    }
}