import java.util.Objects;

public class Term extends BasicTerm {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Term)) return false;
        if (!super.equals(o)) return false;
        Term term = (Term) o;
        return getDay() == term.getDay();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDay());
    }

    private Day day;

    public Term(int hour, int minute, Day day) {
        super(hour, minute, 90);
        this.day = day;
    }

    public Term(int hour, int minute, Day day, int duration) {
        super(hour, minute, duration);
        this.day = day;
    }

    public Term endTerm(Term termin) {
        Term termin2 = new Term(getHour(), getMinute(), day);
        termin2.setDuration(termin.getHour() * 60 + termin.getMinute() - getHour() * 60 - getMinute());
        return termin2;
    }

    public Term endTerm() {
        Term termin2 = new Term(getHour() + (getMinute() + getDuration()) / 60, (getMinute() + getDuration()) % 60, day);
        return termin2;
    }

    public boolean equals(Term termin) {
        return (getHour() == termin.getHour() && getMinute() == termin.getMinute() && day == termin.day &&
                getDuration() == termin.getDuration());
    }

    public boolean overlap(Term termin, boolean isBreak) {
        if (!isBreak && !day.equals(termin.day)) return false;
        return (((getHour() * 60 + getMinute() + getDuration() > termin.getHour() * 60 + termin.getMinute()) && earlierThan(termin)) ||
                ((getHour() * 60 + getMinute() < termin.getHour() * 60 + termin.getMinute() + termin.getDuration()) && laterThan(termin)) ||
                getHour() * 60 + getMinute() == termin.getHour() * 60 + termin.getMinute());
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
