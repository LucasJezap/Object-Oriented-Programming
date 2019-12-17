package parser;

/**
 * @see AbstractEntryFactory
 */
public class UnpublishedEntryFactory extends AbstractEntryFactory {
    /**
     * Simple method initializing all required and optional attributes by putting them in a HashMap with second
     * value undefined (here i just chose to put "")
     */
    public void initEntry() {
        super.getRequiredAttributes().put("author", "");
        super.getRequiredAttributes().put("title", "");
        super.getRequiredAttributes().put("note", "");
        super.getOptionalAttributes().put("month", "");
        super.getOptionalAttributes().put("year", "");
        super.getOptionalAttributes().put("crossref", "");
        super.getOptionalAttributes().put("key", "");
    }

}
