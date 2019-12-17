package parser;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class AbstractEntryFactoryTest {

    @Test
    public void fixLine() {
        String x = "{\" random text 'random' mon$y! = ~ \\ money}";
        x = new ArticleEntryFactory().fixLine("nothing", x);
        assertEquals(" random text random mony money", x);
    }

    @Test
    public void fillEntry() {
        String input = "@ARTICLE{article-minimal,\n" +
                "   author = {L[eslie] A. Aamport},\n" +
                "   title = {The Gnats and Gnus Document Preparation System},\n" +
                "   journal = {\\mbox{G-Animal's} Journal},\n" +
                "   year = 1986,\n" +
                "}";
        AbstractEntryFactory entry = new ArticleEntryFactory();
        entry.initEntry();
        HashMap<String, String> map = new HashMap<>();
        entry.fillEntry(input, map, null);
        assertEquals("ARTICLE", entry.getType());
        assertEquals("article-minimal", entry.getSubtype());
        assertEquals("Leslie A. Aamport", entry.getRequiredAttributes().get("author"));
        assertEquals("The Gnats and Gnus Document Preparation System", entry.getRequiredAttributes().get("title"));
        assertEquals("G-Animals Journal", entry.getRequiredAttributes().get("journal"));
        assertEquals("1986", entry.getRequiredAttributes().get("year"));
    }

    @Test
    public void checkRequired() {
        String input1 = "@ARTICLE{article-minimal,\n" +
                "   author = {L[eslie] A. Aamport},\n" +
                "   title = {The Gnats and Gnus Document Preparation System},\n" +
                "   journal = {\\mbox{G-Animal's} Journal},\n" +
                "   year = 1986,\n" +
                "}";
        AbstractEntryFactory entry = new ArticleEntryFactory();
        HashMap<String, String> map = new HashMap<>();
        entry.initEntry();
        entry.fillEntry(input1, map, null);
        assertTrue(entry.checkRequired());
        String input2 = "@INCOLLECTION{incollection-minimal,\n" +
                "   author = \"Daniel D. Lincoll\",\n" +
                "   title = \"Semigroups of Recurrences\",\n" +
                "   booktitle = \"High Speed Computer and Algorithm Organization\",\n" +
                "   publisher = \"Academic Press\",\n" +
                "   year = 1977,\n" +
                "}";
        AbstractEntryFactory entry2 = new IncollectionEntryFactory();
        entry2.initEntry();
        entry2.fillEntry(input2, map, null);
        assertTrue(entry2.checkRequired());
    }

    @Test
    public void getRecord() {
        String expected = "-------------------------------------------------------------------------------------------------------\n" +
                "|   ARTICLE(article-minimal)                                                                          |\n" +
                "-------------------------------------------------------------------------------------------------------\n" +
                "|   journal          |   G-Animals Journal                                                            |\n" +
                "-------------------------------------------------------------------------------------------------------\n" +
                "|   year             |   1986                                                                         |\n" +
                "-------------------------------------------------------------------------------------------------------\n" +
                "|   author           |   Leslie A. Aamport                                                            |\n" +
                "-------------------------------------------------------------------------------------------------------\n" +
                "|   title            |   The Gnats and Gnus Document Preparation System                               |\n" +
                "-------------------------------------------------------------------------------------------------------\n";
        String input = "@ARTICLE{article-minimal,\n" +
                "   author = {L[eslie] A. Aamport},\n" +
                "   title = {The Gnats and Gnus Document Preparation System},\n" +
                "   journal = {\\mbox{G-Animal's} Journal},\n" +
                "   year = 1986,\n" +
                "}";
        HashMap<String, String> map = new HashMap<>();
        AbstractEntryFactory entry = new ArticleEntryFactory();
        entry.initEntry();
        entry.fillEntry(input, map, null);
        assertEquals(expected, entry.getRecord());
    }
}