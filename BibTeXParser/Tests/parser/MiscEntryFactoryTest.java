package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class MiscEntryFactoryTest {

    @Test
    public void initEntry() {
        AbstractEntryFactory entry = new MiscEntryFactory();
        entry.initEntry();
        assertTrue(entry.getOptionalAttributes().containsKey("author"));
        assertTrue(entry.getOptionalAttributes().containsKey("title"));
        assertTrue(entry.getOptionalAttributes().containsKey("howpublished"));
        assertTrue(entry.getOptionalAttributes().containsKey("month"));
        assertTrue(entry.getOptionalAttributes().containsKey("year"));
        assertTrue(entry.getOptionalAttributes().containsKey("note"));
        assertTrue(entry.getOptionalAttributes().containsKey("key"));
    }
}