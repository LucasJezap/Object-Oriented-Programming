package parser;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class IntegrationTests {

    @Test
    public void endToEndTest() {
        String[] args = {"C:\\Users\\yyy\\Desktop\\xampl.bib", "BOOK", "Knuth"};
        List<AbstractEntryFactory> entries;
        try {
            Parser parser = new Parser();
            entries = parser.parse(args[0]);
            TypeVisitor typeVisitor = new TypeVisitor();
            String[] types = {args[1]};
            entries = typeVisitor.visit(types, entries);
            AuthorVisitor authorVisitor = new AuthorVisitor();
            String[] authors = {args[2]};
            entries = authorVisitor.visit(authors, entries);
            assertEquals(3, entries.size());
            String result = "";
            for (AbstractEntryFactory entry : entries)
                result += entry.getRecord();
            assertTrue(result.contains("-------------------------------------------------------------------------------------------------------\n" +
                    "|   BOOK(book-minimal)                                                                                |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   year             |   1981                                                                         |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   author           |   Donald E. Knuth                                                              |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   publisher        |   Addison-Wesley                                                               |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   title            |   Seminumerical Algorithms                                                     |\n" +
                    "-------------------------------------------------------------------------------------------------------\n"));
            assertTrue(result.contains("-------------------------------------------------------------------------------------------------------\n" +
                    "|   BOOK(book-full)                                                                                   |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   year             |   1981                                                                         |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   author           |   Donald E. Knuth                                                              |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   publisher        |   Addison-Wesley                                                               |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   title            |   Seminumerical Algorithms                                                     |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   volume           |   2                                                                            |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   note             |   This is a full BOOK entry                                                    |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   address          |   Reading, Massachusetts                                                       |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   month            |   10jan                                                                        |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   series           |   The Art of Computer Programming                                              |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   edition          |   Second                                                                       |\n" +
                    "-------------------------------------------------------------------------------------------------------"));
            assertTrue(result.contains("-------------------------------------------------------------------------------------------------------\n" +
                    "|   BOOK(whole-set)                                                                                   |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   year             |   1968                                                                         |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   author           |   Donald E. Knuth                                                              |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   publisher        |   Addison-Wesley                                                               |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   title            |   The Art of Computer Programming                                              |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   note             |   Seven volumes planned (this is a cross-referenced set of BOOKs)              |\n" +
                    "-------------------------------------------------------------------------------------------------------\n" +
                    "|   series           |   Four volumes                                                                 |\n" +
                    "-------------------------------------------------------------------------------------------------------\n"));
            assertFalse(result.contains("@STRING{STOC-key = \"OX{\\singleletter{stoc}}\"}"));
            assertFalse(result.contains("@preamble{ \"\\newcommand{\\noopsort}[1]{} \""));
        } catch (Exception e) {
        }
    }

    @Test
    public void parserTest() {
        String arg = "C:\\Users\\yyy\\Desktop\\xampl.bib";
        try {
            Parser parser = new Parser();
            List<AbstractEntryFactory> entries = parser.parse(arg);
            assertEquals(30, entries.size());
        } catch (IOException e) {
        }
    }

    @Test
    public void oneAuthorVisitorTest() {
        String[] authors = {"Donald Knuth"};
        String arg = "C:\\Users\\yyy\\Desktop\\xampl.bib";
        try {
            Parser parser = new Parser();
            List<AbstractEntryFactory> entries = parser.parse(arg);
            AuthorVisitor authorVisitor = new AuthorVisitor();
            entries = authorVisitor.visit(authors, entries);
            assertEquals(5, entries.size());
            for (AbstractEntryFactory entry : entries) {
                assertTrue(Arrays.asList(entry.getAuthorsNames()).contains("Donald"));
                assertTrue(Arrays.asList(entry.getAuthorsSurnames()).contains("Knuth"));
            }
        } catch (IOException e) {
        }
    }

    @Test
    public void oneNonExistingAuthorVisitorTest() {
        String[] authors = {"Donalt Knuth"};
        String arg = "C:\\Users\\yyy\\Desktop\\xampl.bib";
        try {
            Parser parser = new Parser();
            List<AbstractEntryFactory> entries = parser.parse(arg);
            AuthorVisitor authorVisitor = new AuthorVisitor();
            entries = authorVisitor.visit(authors, entries);
            assertEquals(0, entries.size());
        } catch (IOException e) {
        }
    }

    @Test
    public void twoAuthorsVisitorTest() {
        String[] authors = {"Donald Knuth", "Phony-Baloney"};
        String arg = "C:\\Users\\yyy\\Desktop\\xampl.bib";
        try {
            Parser parser = new Parser();
            List<AbstractEntryFactory> entries = parser.parse(arg);
            AuthorVisitor authorVisitor = new AuthorVisitor();
            entries = authorVisitor.visit(authors, entries);
            assertEquals(7, entries.size());
            for (AbstractEntryFactory entry : entries) {
                assertTrue(Arrays.asList(entry.getAuthorsSurnames()).contains("Knuth") ||
                        Arrays.asList(entry.getAuthorsSurnames()).contains("Phony-Baloney"));
            }
        } catch (IOException e) {
        }
    }

    @Test
    public void oneTypeVisitorTest() {
        String[] types = {"BOOK"};
        String arg = "C:\\Users\\yyy\\Desktop\\xampl.bib";
        try {
            Parser parser = new Parser();
            List<AbstractEntryFactory> entries = parser.parse(arg);
            TypeVisitor typeVisitor = new TypeVisitor();
            entries = typeVisitor.visit(types, entries);
            assertEquals(4, entries.size());
            for (AbstractEntryFactory entry : entries) {
                assertEquals("BOOK", entry.getType());
            }
        } catch (IOException e) {
        }
    }

    @Test
    public void oneNonExistingTypeVisitorTest() {
        String[] types = {"BOOKLED"};
        String arg = "C:\\Users\\yyy\\Desktop\\xampl.bib";
        try {
            Parser parser = new Parser();
            List<AbstractEntryFactory> entries = parser.parse(arg);
            TypeVisitor typeVisitor = new TypeVisitor();
            entries = typeVisitor.visit(types, entries);
            assertEquals(0, entries.size());
        } catch (IOException e) {
        }
    }

    @Test
    public void twoTypeVisitorTest() {
        String[] types = {"BOOK", "ARTICLE"};
        String arg = "C:\\Users\\yyy\\Desktop\\xampl.bib";
        try {
            Parser parser = new Parser();
            List<AbstractEntryFactory> entries = parser.parse(arg);
            TypeVisitor typeVisitor = new TypeVisitor();
            entries = typeVisitor.visit(types, entries);
            assertEquals(6, entries.size());
            for (AbstractEntryFactory entry : entries) {
                assertTrue(entry.getType().equals("BOOK") || entry.getType().equals("ARTICLE"));
            }
        } catch (IOException e) {
        }
    }

    @Test
    public void typeAndAuthorVisitorTest() {
        String[] types = {"BOOK"};
        String[] authors = {"Knuth"};
        String arg = "C:\\Users\\yyy\\Desktop\\xampl.bib";
        try {
            Parser parser = new Parser();
            List<AbstractEntryFactory> entries = parser.parse(arg);
            TypeVisitor typeVisitor = new TypeVisitor();
            entries = typeVisitor.visit(types, entries);
            AuthorVisitor authorVisitor = new AuthorVisitor();
            entries = authorVisitor.visit(authors, entries);
            assertEquals(3, entries.size());
            for (AbstractEntryFactory entry : entries) {
                assertEquals("BOOK", entry.getType());
                assertTrue(Arrays.asList(entry.getAuthorsSurnames()).contains("Knuth"));
            }
        } catch (IOException e) {
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingRequiredAttributesTest() {
        String arg = "C:\\Users\\yyy\\Desktop\\xampl2.bib";
        Parser parser = new Parser();
        try {
            List<AbstractEntryFactory> entries = parser.parse(arg);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongArgumentsTest() {
        String[] args = {"C:\\Users\\yyy\\Desktop\\xampl.bib", ""};
        try {
            Main.main(args);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }
}