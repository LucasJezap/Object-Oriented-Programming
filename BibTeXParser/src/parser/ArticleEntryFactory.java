package parser;

/**
 * @see AbstractEntryFactory
 */
public class ArticleEntryFactory extends AbstractEntryFactory {
    /**
     * Simple method initializing all required and optional attributes by putting them in a HashMap with second
     * value undefined (here i just chose to put "")
     */
    public void initEntry() {
        super.getRequiredAttributes().put("author", "");
        super.getRequiredAttributes().put("title", "");
        super.getRequiredAttributes().put("journal", "");
        super.getRequiredAttributes().put("year", "");
        super.getOptionalAttributes().put("volume", "");
        super.getOptionalAttributes().put("number", "");
        super.getOptionalAttributes().put("pages", "");
        super.getOptionalAttributes().put("doi", "");
        super.getOptionalAttributes().put("month", "");
        super.getOptionalAttributes().put("note", "");
        super.getOptionalAttributes().put("key", "");
    }

}
