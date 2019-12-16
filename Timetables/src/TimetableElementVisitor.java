public interface TimetableElementVisitor {
    void visit(Lesson lesson);
    void visit(Break break1);
}
