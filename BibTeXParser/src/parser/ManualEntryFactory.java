package parser;

import java.util.LinkedHashSet;

public class ManualEntryFactory extends AbstractEntryFactory {
    /**
     * Simple method initializing all required and optional attributes by putting them in a HashMap with second
     * value undefined (here i just chose to put "")
     */
    public void initEntry() {
        super.getRequiredAttributes().put("title", "");
        super.getOptionalAttributes().put("author", "");
        super.getOptionalAttributes().put("organization", "");
        super.getOptionalAttributes().put("address", "");
        super.getOptionalAttributes().put("edition", "");
        super.getOptionalAttributes().put("month", "");
        super.getOptionalAttributes().put("year", "");
        super.getOptionalAttributes().put("crossref", "");
        super.getOptionalAttributes().put("note", "");
        super.getOptionalAttributes().put("key", "");
    }

}
