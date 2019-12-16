package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class ManualEntryFactoryTest {

    @Test
    public void initEntry() {
        AbstractEntryFactory entry = new ManualEntryFactory();
        entry.initEntry();
        assertTrue(entry.getRequiredAttributes().containsKey("title"));
        assertTrue(entry.getOptionalAttributes().containsKey("author"));
        assertTrue(entry.getOptionalAttributes().containsKey("edition"));
        assertTrue(entry.getOptionalAttributes().containsKey("address"));
        assertTrue(entry.getOptionalAttributes().containsKey("organization"));
        assertTrue(entry.getOptionalAttributes().containsKey("year"));
        assertTrue(entry.getOptionalAttributes().containsKey("month"));
        assertTrue(entry.getOptionalAttributes().containsKey("note"));
        assertTrue(entry.getOptionalAttributes().containsKey("key"));
    }
}