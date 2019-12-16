package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class MastersthesisEntryFactoryTest {

    @Test
    public void initEntry() {
        AbstractEntryFactory entry = new MastersthesisEntryFactory();
        entry.initEntry();
        assertTrue(entry.getRequiredAttributes().containsKey("title"));
        assertTrue(entry.getRequiredAttributes().containsKey("author"));
        assertTrue(entry.getRequiredAttributes().containsKey("school"));
        assertTrue(entry.getRequiredAttributes().containsKey("year"));
        assertTrue(entry.getOptionalAttributes().containsKey("type"));
        assertTrue(entry.getOptionalAttributes().containsKey("address"));
        assertTrue(entry.getOptionalAttributes().containsKey("month"));
        assertTrue(entry.getOptionalAttributes().containsKey("note"));
        assertTrue(entry.getOptionalAttributes().containsKey("key"));
    }
}