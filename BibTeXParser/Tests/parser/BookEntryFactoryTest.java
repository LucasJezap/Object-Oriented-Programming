package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookEntryFactoryTest {

    @Test
    public void initEntry() {
        AbstractEntryFactory entry = new BookEntryFactory();
        entry.initEntry();
        assertTrue(entry.getRequiredAttributes().containsKey("author"));
        assertTrue(entry.getRequiredAttributes().containsKey("title"));
        assertTrue(entry.getRequiredAttributes().containsKey("publisher"));
        assertTrue(entry.getRequiredAttributes().containsKey("year"));
        assertTrue(entry.getOptionalAttributes().containsKey("volume"));
        assertTrue(entry.getOptionalAttributes().containsKey("series"));
        assertTrue(entry.getOptionalAttributes().containsKey("booktitle"));
        assertTrue(entry.getOptionalAttributes().containsKey("address"));
        assertTrue(entry.getOptionalAttributes().containsKey("edition"));
        assertTrue(entry.getOptionalAttributes().containsKey("month"));
        assertTrue(entry.getOptionalAttributes().containsKey("note"));
        assertTrue(entry.getOptionalAttributes().containsKey("key"));
        assertTrue(entry.getOptionalAttributes().containsKey("url"));
    }
}