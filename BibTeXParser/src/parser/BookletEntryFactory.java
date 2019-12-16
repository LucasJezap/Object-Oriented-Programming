package parser;


/**
 * @see AbstractEntryFactory
 */
public class BookletEntryFactory extends AbstractEntryFactory {
    /**
     * Simple method initializing all required and optional attributes by putting them in a HashMap with second
     * value undefined (here i just chose to put "")
     */
    public void initEntry() {
        super.getRequiredAttributes().put("title", "");
        super.getOptionalAttributes().put("author", "");
        super.getOptionalAttributes().put("howpublished", "");
        super.getOptionalAttributes().put("address", "");
        super.getOptionalAttributes().put("month", "");
        super.getOptionalAttributes().put("year", "");
        super.getOptionalAttributes().put("note", "");
        super.getOptionalAttributes().put("key", "");
    }

}
