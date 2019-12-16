package parser;

/**
 * @see AbstractEntryFactory
 */
public class MiscEntryFactory extends AbstractEntryFactory {
    /**
     * Simple method initializing all required and optional attributes by putting them in a HashMap with second
     * value undefined (here i just chose to put "")
     */
    public void initEntry() {
        super.getOptionalAttributes().put("author", "");
        super.getOptionalAttributes().put("title", "");
        super.getOptionalAttributes().put("howpublished", "");
        super.getOptionalAttributes().put("month", "");
        super.getOptionalAttributes().put("year", "");
        super.getOptionalAttributes().put("note", "");
        super.getOptionalAttributes().put("key", "");
    }

}
