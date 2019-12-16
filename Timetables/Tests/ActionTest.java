import org.junit.Test;

import static org.junit.Assert.*;

public class ActionTest {

    @Test
    public void makeChanges() {
        ITimetable timetable = new Timetable1();
        Lesson lesson = new Lesson(timetable, new Term(8, 0, Day.MON, 90), "Obiektowe", "Polak", 2);
        Lesson lesson2 = new Lesson(timetable, new Term(9, 30, Day.MON, 90), "Statystyka", "Smołka", 2);
        Action[] actions = {Action.DAY_LATER, Action.DAY_EARLIER, Action.TIME_EARLIER, Action.TIME_LATER};
        Action.makeChanges(lesson, actions);
        Action.makeChanges(lesson2, actions);
        String time = "Obiektowe (Poniedziałek 8:00-9:30)\nDrugi rok studiów stacjonarnych\nProwadzący: Polak";
        String time2 = "Statystyka (Poniedziałek 8:00-9:30)\nDrugi rok studiów stacjonarnych\nProwadzący: Polak";
        //assertEquals(lesson.toString());
    }
}