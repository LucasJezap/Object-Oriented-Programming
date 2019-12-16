import java.util.List;

public class Timetable1 extends AbstractTimetable {

    public boolean put(Lesson lesson) {
        if (canBeTransferredTo(lesson.getTerm(), lesson.isFull_time())) {
            super.lessons.put(lesson.getTerm(), lesson);
            return true;
        } else if (busy(lesson.getTerm())) {
            throw new IllegalArgumentException("The term " + lesson.getTerm() + " is busy!!!");
        }
        return false;
    }

    public String toString() {
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8, 0, firstDay);
        Term lastTerm = new Term(21, 30, lastDay);
        Day day = null;
        Term term = null;
        String result = "";
        int i = 0;
        for (day = firstDay; i++ != 7; day = day.nextDay())
            result += String.format("%1$32s", day.toString());
        result += "\n";
        for (term = firstTerm; term.earlierThan(lastTerm); term = term.endTerm()) {
            result += term.fullHours();
            i = 0;
            for (day = firstDay; i++ != 7; day = day.nextDay()) {
                term.setDay(day);
                if (busy(term)) {
                    Lesson tmp = (Lesson) get(term);
                    result += String.format("*%1$30s*", tmp.getName());
                } else {
                    result += String.format("*%31s", "*");
                }
            }
            result += "\n";
        }
        return result;
    }

}
