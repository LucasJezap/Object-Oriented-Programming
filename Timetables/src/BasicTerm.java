import java.util.Objects;

public class BasicTerm implements Comparable<BasicTerm> {
    private int hour;
    private int minute;
    private int duration;

    public BasicTerm(int hour, int minute, int duration) {
        this.hour = hour;
        this.minute = minute;
        this.duration = duration;
    }

    @Override
    public int compareTo(BasicTerm b) {
        if (hour<b.getHour()){
            return -1;
        }
        else if (hour>b.getHour()) {
            return 1;
        }
        else {
            if(minute<b.getMinute()) {
                return -1;
            }
            else if (minute>b.getMinute()) {
                return 1;
            }
            else {
                if(duration<b.getDuration()) {
                    return -1;
                }
                else if (duration>b.getDuration()) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicTerm)) return false;
        BasicTerm basicTerm = (BasicTerm) o;
        return getHour() == basicTerm.getHour() &&
                getMinute() == basicTerm.getMinute() &&
                getDuration() == basicTerm.getDuration();
    }

    public String fullHours() {
        int tmp = (hour * 60 + minute + duration);
        BasicTerm endHour = new BasicTerm(tmp / 60, tmp % 60, duration);
        String time = toStringHour(this);
        time += "-";
        time += toStringHour(endHour);
        return time;
    }

    public String toStringHour(BasicTerm termin) {
        String time = "";
        time += (termin.hour < 10) ? "0" + termin.hour : termin.hour;
        time += ":";
        time += (termin.minute > 9) ? termin.minute : "0" + termin.minute;
        return time;
    }

    public String toString() {
        String time = "" + hour + ":";
        time += (minute < 10) ? "0" + minute : minute;
        time += " [" + duration + "]";
        return time;
    }

    public boolean earlierThan(BasicTerm termin) {
        return (hour < termin.hour || (hour == termin.hour && minute < termin.minute));
    }

    public boolean laterThan(BasicTerm termin) {
        return (hour > termin.hour || (hour == termin.hour && minute > termin.minute));
    }

    public BasicTerm endTerm(BasicTerm termin) {
        BasicTerm termin2 = new BasicTerm(hour, minute, termin.hour * 60 + termin.minute - hour * 60 - minute);
        return termin2;
    }

    public BasicTerm endTerm() {
        BasicTerm termin2 = new BasicTerm(hour + (minute + duration) / 60, (minute + duration) % 60, duration);
        return termin2;
    }

    public int toMinutes() {
        return this.hour * 60 + this.minute;
    }

    public boolean overlap(BasicTerm termin, boolean isBreak) {
        return (((hour * 60 + minute + duration > termin.hour * 60 + termin.minute) && earlierThan(termin)) ||
                ((hour * 60 + minute < termin.hour * 60 + termin.minute + termin.duration) && laterThan(termin)) ||
                hour * 60 + minute == termin.hour * 60 + termin.minute);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
