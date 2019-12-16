import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Timetable2 extends AbstractTimetable implements VisitableTimetableElement {
    private Set<Break> breaks = new TreeSet<Break>();
    private boolean skipBreaks;

    public Timetable2(Set <Break> breaks, boolean skipBreaks) {
        this.breaks = breaks;
        this.skipBreaks = skipBreaks;
    }

    public boolean breakTime(Term term) {
        for (Break schoolBreak :    breaks) {
            if (term.overlap(schoolBreak.getActualTerm(), true)) {
                if (skipBreaks) {
                    term.setHour((term.getHour() * 60 + term.getMinute() + schoolBreak.getActualTerm().getDuration()) / 60);
                    term.setMinute((term.getHour() * 60 + term.getMinute() + schoolBreak.getActualTerm().getDuration()) % 60);
                }
                return true;
            }
        }
        return false;
    }

    public boolean put(Lesson lesson) {
        Term term = lesson.getTerm();
        if (canBeTransferredTo(term, lesson.isFull_time())) {
            if (breakTime(term)) {
                if (skipBreaks) {
                    super.lessons.put(lesson.getTerm(), lesson);
                    return true;
                } else {
                    return false;
                }
            } else {
                super.lessons.put(lesson.getTerm(), lesson);
                return true;
            }
        } else if (busy(lesson.getTerm())) {
            throw new IllegalArgumentException("The term " + lesson.getTerm() + " is busy!!!");
        }
        return false;
    }

    public String toString() {
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8, 0, firstDay);
        Term lastTerm = new Term(21, 0, lastDay);
        Day day = null;
        Term term = null;
        String result = "";
        int i = 0;
        for (day = firstDay; i++ != 7; day = day.nextDay())
            result += String.format("%1$32s", day.toString());
        result += "\n";
        for (term = firstTerm; term.earlierThan(lastTerm); term = term.endTerm()) {
            if (breakTime(term)) {
                for (Break schoolBreak : breaks) {
                    if (term.toMinutes() == schoolBreak.getActualTerm().toMinutes() + schoolBreak.getActualTerm().getDuration()) {
                        result += schoolBreak.getActualTerm().fullHours();
                        i = 0;
                        for (day = firstDay; i++ != 7; day = day.nextDay()) {
                            term.setDay(day);
                            result += String.format("*%31s", "*").replace(" ", "-");
                        }
                    }
                }
                result += "\n";
            }
            result += term.fullHours();
            i = 0;
            for (day = firstDay; i++ != 7; day = day.nextDay()) {
                term.setDay(day);
                if (busy(term)) {
                    Lesson tmp = (Lesson) get(term);
                    Term tmpTerm=tmp.getTerm();
                    if(tmpTerm.getHour()*60+tmpTerm.getMinute()>=term.getHour()*60+term.getMinute())
                        result += String.format("*%1$30s*", tmp.getName());
                    else
                        result += String.format("*%31s", "*");
                } else {
                    result += String.format("*%31s", "*");
                }
            }
            result += "\n";
        }
        return result;
    }

    public void accept(TimetableElementVisitor visitor) {
        for (Map.Entry<Term, Lesson> lesson : lessons.entrySet()) {
            visitor.visit(lesson.getValue());
        }
        for (Break schoolBreak : breaks) {
            visitor.visit(schoolBreak);
        }
    }

    public Set<Break> getBreaks() {
        return breaks;
    }

    public void setBreaks(Set<Break> breaks) {
        this.breaks = breaks;
    }

    public boolean isSkipBreaks() {
        return skipBreaks;
    }

    public void setSkipBreaks(boolean skipBreaks) {
        this.skipBreaks = skipBreaks;
    }
}
