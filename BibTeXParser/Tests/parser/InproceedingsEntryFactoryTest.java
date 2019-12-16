package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class InproceedingsEntryFactoryTest {

    @Test
    public void initEntry() {
        AbstractEntryFactory entry = new InproceedingsEntryFactory();
        entry.initEntry();
        assertTrue(entry.getRequiredAttributes().containsKey("author"));
        assertTrue(entry.getRequiredAttributes().containsKey("title"));
        assertTrue(entry.getRequiredAttributes().containsKey("booktitle"));
        assertTrue(entry.getRequiredAttributes().containsKey("year"));
        assertTrue(entry.getOptionalAttributes().containsKey("editor"));
        assertTrue(entry.getOptionalAttributes().containsKey("volume"));
        assertTrue(entry.getOptionalAttributes().containsKey("series"));
        assertTrue(entry.getOptionalAttributes().containsKey("pages"));
        assertTrue(entry.getOptionalAttributes().containsKey("address"));
        assertTrue(entry.getOptionalAttributes().containsKey("organization"));
        assertTrue(entry.getOptionalAttributes().containsKey("publisher"));
        assertTrue(entry.getOptionalAttributes().containsKey("month"));
        assertTrue(entry.getOptionalAttributes().containsKey("note"));
        assertTrue(entry.getOptionalAttributes().containsKey("key"));
    }
}