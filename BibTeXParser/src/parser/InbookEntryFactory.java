package parser;

/**
 * @see AbstractEntryFactory
 */
public class InbookEntryFactory extends AbstractEntryFactory {
    /**
     * Simple method initializing all required and optional attributes by putting them in a HashMap with second
     * value undefined (here i just chose to put "")
     */
    public void initEntry() {
        super.getRequiredAttributes().put("author", "");
        super.getRequiredAttributes().put("title", "");
        super.getRequiredAttributes().put("chapter", "");
        super.getRequiredAttributes().put("publisher", "");
        super.getRequiredAttributes().put("year", "");
        super.getOptionalAttributes().put("volume", "");
        super.getOptionalAttributes().put("series", "");
        super.getOptionalAttributes().put("type", "");
        super.getOptionalAttributes().put("address", "");
        super.getOptionalAttributes().put("edition", "");
        super.getOptionalAttributes().put("month", "");
        super.getOptionalAttributes().put("note", "");
        super.getOptionalAttributes().put("key", "");
    }

}
