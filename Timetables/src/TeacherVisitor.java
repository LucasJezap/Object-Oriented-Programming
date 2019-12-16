public class TeacherVisitor implements TimetableElementVisitor {
    private String name;
    private Day day;
    public TeacherVisitor(String name, Day day) {
        this.name=name;
        this.day=day;
    }

    public void visit(Break break1) {
        System.out.println("Just a break");
    }

    public void visit(Lesson lesson) {
        if (lesson.getTeacherName().equals(name))
            System.out.println(lesson.getTerm().fullHours()+" "+lesson.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
