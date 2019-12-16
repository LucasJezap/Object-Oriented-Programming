package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class TechreportEntryFactoryTest {

    @Test
    public void initEntry() {
        AbstractEntryFactory entry = new TechreportEntryFactory();
        entry.initEntry();
        assertTrue(entry.getRequiredAttributes().containsKey("title"));
        assertTrue(entry.getRequiredAttributes().containsKey("author"));
        assertTrue(entry.getRequiredAttributes().containsKey("institution"));
        assertTrue(entry.getRequiredAttributes().containsKey("year"));
        assertTrue(entry.getOptionalAttributes().containsKey("editor"));
        assertTrue(entry.getOptionalAttributes().containsKey("type"));
        assertTrue(entry.getOptionalAttributes().containsKey("volume"));
        assertTrue(entry.getOptionalAttributes().containsKey("series"));
        assertTrue(entry.getOptionalAttributes().containsKey("address"));
        assertTrue(entry.getOptionalAttributes().containsKey("month"));
        assertTrue(entry.getOptionalAttributes().containsKey("organization"));
        assertTrue(entry.getOptionalAttributes().containsKey("publisher"));
        assertTrue(entry.getOptionalAttributes().containsKey("note"));
        assertTrue(entry.getOptionalAttributes().containsKey("key"));
    }
}