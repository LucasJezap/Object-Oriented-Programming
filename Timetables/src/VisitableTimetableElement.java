public interface VisitableTimetableElement {
    void accept(TimetableElementVisitor visitor);
}
