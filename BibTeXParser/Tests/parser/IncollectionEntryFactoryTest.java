package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class IncollectionEntryFactoryTest {

    @Test
    public void initEntry() {
        AbstractEntryFactory entry = new IncollectionEntryFactory();
        entry.initEntry();
        assertTrue(entry.getRequiredAttributes().containsKey("author"));
        assertTrue(entry.getRequiredAttributes().containsKey("title"));
        assertTrue(entry.getRequiredAttributes().containsKey("publisher"));
        assertTrue(entry.getRequiredAttributes().containsKey("booktitle"));
        assertTrue(entry.getRequiredAttributes().containsKey("year"));
        assertTrue(entry.getOptionalAttributes().containsKey("editor"));
        assertTrue(entry.getOptionalAttributes().containsKey("volume"));
        assertTrue(entry.getOptionalAttributes().containsKey("series"));
        assertTrue(entry.getOptionalAttributes().containsKey("type"));
        assertTrue(entry.getOptionalAttributes().containsKey("chapter"));
        assertTrue(entry.getOptionalAttributes().containsKey("pages"));
        assertTrue(entry.getOptionalAttributes().containsKey("address"));
        assertTrue(entry.getOptionalAttributes().containsKey("edition"));
        assertTrue(entry.getOptionalAttributes().containsKey("month"));
        assertTrue(entry.getOptionalAttributes().containsKey("note"));
        assertTrue(entry.getOptionalAttributes().containsKey("key"));
    }
}