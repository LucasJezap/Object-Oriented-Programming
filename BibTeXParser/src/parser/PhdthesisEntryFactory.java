package parser;

/**
 * @see AbstractEntryFactory
 */
public class PhdthesisEntryFactory extends AbstractEntryFactory {
    /**
     * Simple method initializing all required and optional attributes by putting them in a HashMap with second
     * value undefined (here i just chose to put "")
     */
    public void initEntry() {
        super.getRequiredAttributes().put("author", "");
        super.getRequiredAttributes().put("title", "");
        super.getRequiredAttributes().put("school", "");
        super.getRequiredAttributes().put("year", "");
        super.getOptionalAttributes().put("type", "");
        super.getOptionalAttributes().put("address", "");
        super.getOptionalAttributes().put("month", "");
        super.getOptionalAttributes().put("note", "");
        super.getOptionalAttributes().put("key", "");
    }

}
