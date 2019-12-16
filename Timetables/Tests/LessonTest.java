import org.junit.Test;

import static org.junit.Assert.*;

public class LessonTest {

    @Test
    public void repairMinute() {
        ITimetable timetable = new Timetable1();
        Lesson lesson = new Lesson(timetable, new Term(8, 5, Day.MON, 90), "Obiektowe", "Polak", 2);
        assertEquals("05", lesson.repairMinute(5));
    }

    @Test
    public void testToString() {
        ITimetable timetable = new Timetable1();
        Lesson lesson = new Lesson(timetable, new Term(8, 0, Day.MON, 90), "Obiektowe", "Polak", 2);
        String time = "Obiektowe (Poniedziałek 8:00-9:30)\nDrugi rok studiów stacjonarnych\nProwadzący: Polak";
        assertEquals(time, lesson.toString());
    }

    @Test
    public void earlierDay() {
        ITimetable timetable = new Timetable1();
        Lesson lesson = new Lesson(timetable, new Term(8, 0, Day.MON, 90), "Obiektowe", "Polak", 2);
        Lesson lesson2 = new Lesson(timetable, new Term(9, 30, Day.TUE, 90), "Statystyka", "Smołka", 2);
        Lesson lesson3 = new Lesson(timetable, new Term(8, 0, Day.TUE, 90), "Geometryczne", "Głut", 2);
        timetable.put(lesson);
        timetable.put(lesson2);
        timetable.put(lesson3);
        assertTrue(lesson2.earlierDay());
        assertFalse(lesson3.earlierDay());
    }

    @Test
    public void laterDay() {
        ITimetable timetable = new Timetable1();
        Lesson lesson = new Lesson(timetable, new Term(8, 0, Day.MON, 90), "Obiektowe", "Polak", 2);
        Lesson lesson2 = new Lesson(timetable, new Term(9, 30, Day.MON, 90), "Statystyka", "Smołka", 2);
        Lesson lesson3 = new Lesson(timetable, new Term(8, 0, Day.TUE, 90), "Geometryczne", "Głut", 2);
        timetable.put(lesson);
        timetable.put(lesson2);
        timetable.put(lesson3);
        assertTrue(lesson2.laterDay());
        assertFalse(lesson.laterDay());
    }

    @Test
    public void earlierTime() {
        ITimetable timetable = new Timetable1();
        Lesson lesson = new Lesson(timetable, new Term(8, 0, Day.MON, 90), "Obiektowe", "Polak", 2);
        Lesson lesson2 = new Lesson(timetable, new Term(9, 30, Day.MON, 90), "Statystyka", "Smołka", 2);
        Lesson lesson3 = new Lesson(timetable, new Term(9, 30, Day.TUE, 90), "Geometryczne", "Głut", 2);
        timetable.put(lesson);
        timetable.put(lesson2);
        timetable.put(lesson3);
        assertTrue(lesson3.earlierTime());
        assertFalse(lesson2.earlierTime());
        assertFalse(lesson.earlierTime());
    }

    @Test
    public void laterTime() {
        ITimetable timetable = new Timetable1();
        Lesson lesson = new Lesson(timetable, new Term(8, 0, Day.MON, 90), "Obiektowe", "Polak", 2);
        Lesson lesson2 = new Lesson(timetable, new Term(9, 30, Day.MON, 90), "Statystyka", "Smołka", 2);
        Lesson lesson3 = new Lesson(timetable, new Term(9, 30, Day.TUE, 90), "Geometryczne", "Głut", 2);
        timetable.put(lesson);
        timetable.put(lesson2);
        timetable.put(lesson3);
        assertTrue(lesson3.laterTime());
        assertFalse(lesson.laterTime());
        assertTrue(lesson2.laterTime());
    }
}