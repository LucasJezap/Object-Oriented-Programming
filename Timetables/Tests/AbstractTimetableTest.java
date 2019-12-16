import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class AbstractTimetableTest {

    @Test
    public void canBeTransferredTo() {
        AbstractTimetable timetable1 = new Timetable1();
        Set<Break> breaks = new TreeSet<Break>();
        breaks.add(new Break(new Term(9, 30, Day.MON, 5)));
        breaks.add(new Break(new Term(11, 5, Day.MON, 10)));
        AbstractTimetable timetable2 = new Timetable2(breaks, true);
        AbstractTimetable timetable3 = new Timetable2(breaks, false);
        timetable1.put(new Lesson(timetable1, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable1.put(new Lesson(timetable1, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        timetable2.put(new Lesson(timetable2, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable2.put(new Lesson(timetable2, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        timetable3.put(new Lesson(timetable3, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable3.put(new Lesson(timetable3, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        assertTrue(timetable1.canBeTransferredTo(new Term(15, 0, Day.MON), true));
        assertFalse(timetable1.canBeTransferredTo(new Term(9, 0, Day.MON), true));
        assertTrue(timetable1.canBeTransferredTo(new Term(9, 30, Day.MON), true));
        assertTrue(timetable2.canBeTransferredTo(new Term(15, 0, Day.MON), true));
        assertFalse(timetable2.canBeTransferredTo(new Term(9, 0, Day.MON), true));
        assertTrue(timetable2.canBeTransferredTo(new Term(9, 30, Day.MON), true));
        assertTrue(timetable3.canBeTransferredTo(new Term(15, 0, Day.MON), true));
        assertFalse(timetable3.canBeTransferredTo(new Term(9, 0, Day.MON), true));
        assertTrue(timetable3.canBeTransferredTo(new Term(9, 30, Day.MON), true));
    }

    @Test
    public void busy() {
        AbstractTimetable timetable1 = new Timetable1();
        Set<Break> breaks = new TreeSet<Break>();
        breaks.add(new Break(new Term(9, 30, Day.MON, 5)));
        breaks.add(new Break(new Term(11, 5, Day.MON, 10)));
        AbstractTimetable timetable2 = new Timetable2(breaks, true);
        AbstractTimetable timetable3 = new Timetable2(breaks, false);
        timetable1.put(new Lesson(timetable1, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable1.put(new Lesson(timetable1, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        timetable2.put(new Lesson(timetable2, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable2.put(new Lesson(timetable2, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        timetable3.put(new Lesson(timetable3, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable3.put(new Lesson(timetable3, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        assertFalse(timetable1.busy(new Term(15, 0, Day.MON)));
        assertTrue(timetable1.busy(new Term(9, 0, Day.MON)));
        assertFalse(timetable1.busy(new Term(9, 30, Day.MON)));
        assertFalse(timetable2.busy(new Term(15, 0, Day.MON)));
        assertTrue(timetable2.busy(new Term(9, 0, Day.MON)));
        assertFalse(timetable2.busy(new Term(9, 30, Day.MON)));
        assertFalse(timetable3.busy(new Term(15, 0, Day.MON)));
        assertTrue(timetable3.busy(new Term(9, 0, Day.MON)));
        assertFalse(timetable3.busy(new Term(9, 30, Day.MON)));
    }

    @Test
    public void perform() {
        AbstractTimetable timetable1 = new Timetable1();
        Set<Break> breaks = new TreeSet<Break>();
        breaks.add(new Break(new Term(9, 30, Day.MON, 5)));
        breaks.add(new Break(new Term(11, 5, Day.MON, 10)));
        AbstractTimetable timetable2 = new Timetable2(breaks, true);
        AbstractTimetable timetable3 = new Timetable2(breaks, false);
        timetable1.put(new Lesson(timetable1, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable1.put(new Lesson(timetable1, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        timetable2.put(new Lesson(timetable2, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable2.put(new Lesson(timetable2, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        timetable3.put(new Lesson(timetable3, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable3.put(new Lesson(timetable3, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        String[] tmp = new String[2];
        tmp[0] = "d+";
        tmp[1] = "d+";
        Action[] actions = new ActionsParser().parse(tmp);
        timetable1.perform(actions);
        timetable2.perform(actions);
        timetable3.perform(actions);
        assertTrue(timetable1.busy(new Term(8, 30, Day.TUE)));
        assertTrue(timetable1.busy(new Term(13, 0, Day.TUE)));
        assertFalse(timetable1.busy(new Term(8, 30, Day.MON)));
        assertFalse(timetable1.busy(new Term(13, 0, Day.MON)));
        assertTrue(timetable2.busy(new Term(8, 30, Day.TUE)));
        assertTrue(timetable2.busy(new Term(13, 0, Day.TUE)));
        assertFalse(timetable2.busy(new Term(8, 30, Day.MON)));
        assertFalse(timetable2.busy(new Term(13, 0, Day.MON)));
        assertTrue(timetable3.busy(new Term(8, 30, Day.TUE)));
        assertTrue(timetable3.busy(new Term(13, 0, Day.TUE)));
        assertFalse(timetable3.busy(new Term(8, 30, Day.MON)));
        assertFalse(timetable3.busy(new Term(13, 0, Day.MON)));
    }

    @Test
    public void get() {
        AbstractTimetable timetable1 = new Timetable1();
        Set<Break> breaks = new TreeSet<Break>();
        breaks.add(new Break(new Term(9, 30, Day.MON, 5)));
        breaks.add(new Break(new Term(11, 5, Day.MON, 10)));
        AbstractTimetable timetable2 = new Timetable2(breaks, true);
        AbstractTimetable timetable3 = new Timetable2(breaks, false);
        timetable1.put(new Lesson(timetable1, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable1.put(new Lesson(timetable1, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        timetable2.put(new Lesson(timetable2, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable2.put(new Lesson(timetable2, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        timetable3.put(new Lesson(timetable3, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2));
        timetable3.put(new Lesson(timetable3, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        Lesson tmp = (Lesson) timetable1.get(new Term(8, 30, Day.MON));
        assertTrue(tmp.equals
                (new Lesson(timetable1, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2)));
        assertEquals(timetable1.get(new Term(17, 30, Day.MON)), null);
        Lesson tmp2 = (Lesson) timetable2.get(new Term(8, 30, Day.MON));
        assertTrue(tmp2.equals
                (new Lesson(timetable2, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2)));
        assertEquals(timetable2.get(new Term(17, 30, Day.MON)), null);
        Lesson tmp3 = (Lesson) timetable3.get(new Term(8, 30, Day.MON));
        assertTrue(tmp3.equals
                (new Lesson(timetable3, new Term(8, 0, Day.MON), "Fizyka wykład", "Kąkol", 2)));
        assertEquals(timetable3.get(new Term(17, 30, Day.MON)), null);
    }
}