package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class LineMatcherTest {

    @Test
    public void getMatcher1() {
        LineMatcher lm = new LineMatcher();
        String s1 = " random text ";
        StringBuilder type = new StringBuilder();
        StringBuilder subtype = new StringBuilder();
        lm.getMatcher1(s1, type, subtype);
        assertEquals("", type.toString());
        assertEquals("", subtype.toString());
        String s2 = "@INBOOK{inbook-minimal,";
        StringBuilder type2 = new StringBuilder();
        StringBuilder subtype2 = new StringBuilder();
        lm.getMatcher1(s2, type2, subtype2);
        assertEquals("INBOOK", type2.toString());
        assertEquals("inbook-minimal", subtype2.toString());
    }

    @Test
    public void getMatcher2() {
        LineMatcher lm = new LineMatcher();
        String s1 = " random text ";
        StringBuilder type = new StringBuilder();
        StringBuilder subtype = new StringBuilder();
        lm.getMatcher2(s1, type, subtype);
        assertEquals("", type.toString());
        assertEquals("", subtype.toString());
        String s2 = "@STRING{STOC-key = \"OX{\\singleletter{stoc}}\"}";
        StringBuilder type2 = new StringBuilder();
        StringBuilder subtype2 = new StringBuilder();
        lm.getMatcher2(s2, type2, subtype2);
        assertEquals("STOC-key", type2.toString());
        assertEquals("OX{\\singleletter{stoc}}", subtype2.toString());
    }

    @Test
    public void doStringMatches() {
        LineMatcher lm = new LineMatcher();
        String s1 = " random text ";
        assertFalse(lm.doStringMatches(s1));
        String s2 = "@STRING{STOC-key = \"OX{\\singleletter{stoc}}\"}";
        assertTrue(lm.doStringMatches(s2));
    }

    @Test
    public void doRecordMatches() {
        LineMatcher lm = new LineMatcher();
        String s1 = " random text ";
        assertFalse(lm.doRecordMatches(s1));
        String s2 = "@INPROCEEDINGS{inproceedings-minimal,";
        assertTrue(lm.doRecordMatches(s2));
    }

    @Test
    public void doRecordEndMatches() {
        LineMatcher lm = new LineMatcher();
        String s1 = " random text ";
        assertFalse(lm.doRecordEndMatches(s1));
        String s2 = "}";
        assertTrue(lm.doRecordEndMatches(s2));
    }
}