public class Break implements VisitableTimetableElement, Comparable<Break> {
    private BasicTerm term;

    public Break(BasicTerm term) {
        this.term = term;
    }

    @Override
    public int compareTo(Break b) {
        return term.compareTo(b.getActualTerm());
    }

    public void accept(TimetableElementVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        return "Przerwa";
    }

    public String getTerm() {
        return term.fullHours();
    }

    public BasicTerm getActualTerm() {
        return term;
    }

    public void setTerm(BasicTerm term) {
        this.term = term;
    }
}
