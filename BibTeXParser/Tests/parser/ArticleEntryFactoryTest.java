package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArticleEntryFactoryTest {

    @Test
    public void initEntry() {
        AbstractEntryFactory entry = new ArticleEntryFactory();
        entry.initEntry();
        assertTrue(entry.getRequiredAttributes().containsKey("author"));
        assertTrue(entry.getRequiredAttributes().containsKey("title"));
        assertTrue(entry.getRequiredAttributes().containsKey("journal"));
        assertTrue(entry.getRequiredAttributes().containsKey("year"));
        assertTrue(entry.getOptionalAttributes().containsKey("volume"));
        assertTrue(entry.getOptionalAttributes().containsKey("number"));
        assertTrue(entry.getOptionalAttributes().containsKey("pages"));
        assertTrue(entry.getOptionalAttributes().containsKey("month"));
        assertTrue(entry.getOptionalAttributes().containsKey("doi"));
        assertTrue(entry.getOptionalAttributes().containsKey("note"));
        assertTrue(entry.getOptionalAttributes().containsKey("key"));
    }
}