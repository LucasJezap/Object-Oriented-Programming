package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnpublishedEntryFactoryTest {

    @Test
    public void initEntry() {
        AbstractEntryFactory entry = new UnpublishedEntryFactory();
        entry.initEntry();
        assertTrue(entry.getRequiredAttributes().containsKey("title"));
        assertTrue(entry.getRequiredAttributes().containsKey("author"));
        assertTrue(entry.getRequiredAttributes().containsKey("note"));
        assertTrue(entry.getOptionalAttributes().containsKey("month"));
        assertTrue(entry.getOptionalAttributes().containsKey("year"));
        assertTrue(entry.getOptionalAttributes().containsKey("key"));
    }
}