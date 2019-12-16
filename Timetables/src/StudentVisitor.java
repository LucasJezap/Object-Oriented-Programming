public class StudentVisitor implements TimetableElementVisitor {
    private int year;
    private boolean full_time;
    private Day day;

    public StudentVisitor(int year, boolean full_time, Day day) {
        this.year = year;
        this.full_time = full_time;
        this.day = day;
    }

    public void visit(Break break1) {
        System.out.println("oh boi");
    }

    public void visit(Lesson lesson) {
        System.out.println("oh boi x2");
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isFull_time() {
        return full_time;
    }

    public void setFull_time(boolean full_time) {
        this.full_time = full_time;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
