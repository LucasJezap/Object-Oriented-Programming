import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractTimetable implements ITimetable {
    public LinkedHashMap<Term, Lesson> lessons = new LinkedHashMap<Term, Lesson>();

    public boolean canBeTransferredTo(Term term, boolean full_time) {
        if (full_time) {
            if (term.getDay()==Day.SAT || term.getDay()==Day.SUN)
                return false;
            else if (term.getDay() == Day.MON || term.getDay() == Day.TUE || term.getDay() == Day.WED || term.getDay() == Day.THU)
                return (term.getHour() * 60 + term.getMinute() + term.getDuration() <= 21 * 60 && term.getHour() * 60 + term.getMinute() >= 8 * 60 && !busy(term));
            else
                return (term.getHour() * 60 + term.getMinute() + term.getDuration() <= 17 * 60 && term.getHour() * 60 + term.getMinute() >= 8 * 60 && !busy(term));
        } else {
            if (term.getDay()==Day.THU || term.getDay()==Day.MON)
                return false;
            else if (term.getDay() == Day.SAT || term.getDay() == Day.SUN)
                return (term.getHour() * 60 + term.getMinute() + term.getDuration() <= 21 * 60 && term.getHour() * 60 + term.getMinute() >= 8 * 60 && !busy(term));
            else
                return (term.getHour() * 60 + term.getMinute() + term.getDuration() <= 21 * 60 && term.getHour() * 60 + term.getMinute() >= 17 * 60 && !busy(term));
        }
    }

    public boolean busy(Term term) {
        for (Map.Entry<Term, Lesson> lesson : lessons.entrySet()) {
            if (term.overlap(lesson.getValue().getTerm(), false)) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean put(Lesson lesson);

    public void perform(Action[] actions) {
        int i = 0;
        List<Lesson> onlyLessons = new ArrayList<Lesson>(lessons.values());
        for (Action action : actions) {
            switch (action) {
                case DAY_EARLIER: {
                    Lesson lesson = onlyLessons.get(i);
                    Term term = lesson.getTerm();
                    if (lesson.earlierDay()) {
                        lessons.remove(term);
                        Term newTerm = new Term(term.getHour(), term.getMinute(), term.getDay().prevDay());
                        Lesson newLesson = new Lesson(this, newTerm, lesson.getName(), lesson.getTeacherName(), lesson.getYear());
                        lesson.setTerm(newTerm);
                        lessons.put(newTerm, newLesson);
                    }
                    break;
                }
                case DAY_LATER: {
                    Lesson lesson = onlyLessons.get(i);
                    Term term = lesson.getTerm();
                    if (lesson.laterDay()) {
                        lessons.remove(term);
                        Term newTerm = new Term(term.getHour(), term.getMinute(), term.getDay().nextDay());
                        Lesson newLesson = new Lesson(this, newTerm, lesson.getName(), lesson.getTeacherName(), lesson.getYear());
                        lesson.setTerm(newTerm);
                        lessons.put(newTerm, newLesson);
                    }
                    break;
                }
                case TIME_EARLIER: {
                    Lesson lesson = onlyLessons.get(i);
                    Term term = lesson.getTerm();
                    if (lesson.earlierTime()) {
                        lessons.remove(term);
                        Term newTerm = new Term((term.getHour() * 60 + term.getMinute() - term.getDuration()) / 60, (term.getHour() * 60 +
                                term.getMinute() - term.getDuration()) % 60, term.getDay());
                        Lesson newLesson = new Lesson(this, newTerm, lesson.getName(), lesson.getTeacherName(), lesson.getYear());
                        lesson.setTerm(newTerm);
                        lessons.put(newTerm, newLesson);
                    }
                    break;
                }
                case TIME_LATER: {
                    Lesson lesson = onlyLessons.get(i);
                    Term term = lesson.getTerm();
                    if (lesson.laterTime()) {
                        lessons.remove(term);
                        Term newTerm = new Term((term.getHour() * 60 + term.getMinute() + term.getDuration()) / 60, (term.getHour() * 60 +
                                term.getMinute() + term.getDuration()) % 60, term.getDay());
                        Lesson newLesson = new Lesson(this, newTerm, lesson.getName(), lesson.getTeacherName(), lesson.getYear());
                        lesson.setTerm(newTerm);
                        lessons.put(newTerm, newLesson);
                    }
                    break;
                }
            }
            if (i + 1 == onlyLessons.size()) i = 0;
            else i += 1;
        }
    }

    public Object get(Term term) {
        for (Map.Entry<Term, Lesson> lesson : lessons.entrySet()) {
            if (term.overlap(lesson.getValue().getTerm(), false)) {
                return lesson.getValue();
            }
        }
        return null;
    }
}
