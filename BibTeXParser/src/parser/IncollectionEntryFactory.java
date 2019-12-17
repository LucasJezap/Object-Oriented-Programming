package parser;

/**
 * @see AbstractEntryFactory
 */
public class IncollectionEntryFactory extends AbstractEntryFactory {
    /**
     * Simple method initializing all required and optional attributes by putting them in a HashMap with second
     * value undefined (here i just chose to put "")
     */
    public void initEntry() {
        super.getRequiredAttributes().put("author", "");
        super.getRequiredAttributes().put("title", "");
        super.getRequiredAttributes().put("booktitle", "");
        super.getRequiredAttributes().put("publisher", "");
        super.getRequiredAttributes().put("year", "");
        super.getOptionalAttributes().put("editor", "");
        super.getOptionalAttributes().put("volume", "");
        super.getOptionalAttributes().put("series", "");
        super.getOptionalAttributes().put("type", "");
        super.getOptionalAttributes().put("chapter", "");
        super.getOptionalAttributes().put("crossref", "");
        super.getOptionalAttributes().put("pages", "");
        super.getOptionalAttributes().put("address", "");
        super.getOptionalAttributes().put("edition", "");
        super.getOptionalAttributes().put("month", "");
        super.getOptionalAttributes().put("note", "");
        super.getOptionalAttributes().put("key", "");
    }

}
