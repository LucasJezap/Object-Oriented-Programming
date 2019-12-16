public class Lesson {
    private Term term;
    private String name;
    private String teacherName;
    private int year;
    private boolean full_time;
    private ITimetable timetable;

    public Lesson(ITimetable timetable, Term term, String name, String teacherName, int year) {
        this.timetable = timetable;
        this.term = term;
        this.name = name;
        this.teacherName = teacherName;
        this.year = year;
        full_time = (term.getDay() == Day.SAT || term.getDay() == Day.SUN ||
                (term.getDay() == Day.FRI && term.getHour() >= 17 && term.getHour() <= 21)) ? false : true;
    }

    public String repairMinute(int minute) {
        if (minute < 10) return "0" + minute;
        else return "" + minute;
    }

    public void accept(TimetableElementVisitor visitor) {
        visitor.visit(this);
    }

    public boolean equals(Lesson lesson) {
        return (term.equals(lesson.term) && name == lesson.name && teacherName == lesson.teacherName &&
                year == lesson.year && full_time == lesson.full_time);
    }

    public String toString() {
        String lesson = "" + name + " (" + term.getDay() + " " + term.getHour() + ":" + repairMinute(term.getMinute()) + "-" +
                ((term.getHour() * 60 + term.getMinute() + term.getDuration()) / 60) + ":" +
                repairMinute((term.getHour() * 60 + term.getMinute() + term.getDuration()) % 60) + ")\n";
        switch (year) {
            case 1: {
                lesson += "Pierwszy";
                break;
            }
            case 2: {
                lesson += "Drugi";
                break;
            }
            case 3: {
                lesson += "Trzeci";
                break;
            }
            case 4: {
                lesson += "Czwarty";
                break;
            }
            case 5: {
                lesson += "Piąty";
                break;
            }
        }
        lesson += " rok studiów ";
        lesson += (full_time) ? "stacjonarnych\n" : "niestacjonarnych\n";
        lesson += "Prowadzący: " + teacherName;
        return lesson;
    }

    public boolean earlierDay() {
        Term newTermin = new Term(term.getHour(), term.getMinute(), term.getDay().prevDay());
        if (timetable.canBeTransferredTo(newTermin, full_time)) {
            return true;
        } else return false;
    }

    public boolean laterDay() {
        Term newTermin = new Term(term.getHour(), term.getMinute(), term.getDay().nextDay());
        if (timetable.canBeTransferredTo(newTermin, full_time)) {
            return true;
        } else return false;
    }

    public boolean earlierTime() {
        Term newTermin = new Term((term.getHour() * 60 + term.getMinute() - term.getDuration()) / 60, (term.getHour() * 60 + term.getMinute() - term.getDuration()) % 60, term.getDay());
        if (timetable.canBeTransferredTo(newTermin, full_time)) {
            return true;
        } else return false;
    }

    public boolean laterTime() {
        Term newTermin = new Term((term.getHour() * 60 + term.getMinute() + term.getDuration()) / 60, (term.getHour() * 60 + term.getMinute() + term.getDuration()) % 60, term.getDay());
        if (timetable.canBeTransferredTo(newTermin, full_time)) {
            return true;
        } else {
            return false;
        }
    }

    public Term getTerm() {
        return term;
    }

    public String getName() {
        return name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getYear() {
        return year;
    }

    public boolean isFull_time() {
        return full_time;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFull_time(boolean full_time) {
        this.full_time = full_time;
    }
}
