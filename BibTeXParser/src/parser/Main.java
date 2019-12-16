package parser;

import java.io.IOException;
import java.util.List;

/**
 * A main class of the BibTeX parser project. Here the program interacts with the arguments given by the user
 * and call parser and appropriate visitors depending on the arguments.
 */
public class Main {

    /**
     * Main method of the Main class. Throw exception if given arguments are not valid.
     *
     * @param args array of three elements: file path, types of records to filter and authors to filter
     */
    public static void main(String[] args) {
        if (args.length != 3 || args[0].equals("")) {
            System.out.println("\nWrong arguments ! Please provide proper arguments next time. \n" +
                    "Arguments should be respectively BibTeX file (.bib), records type, each separated by \"|\",\n" +
                    "authors (names and) surnames, each separated by \"|\", for example: \n" +
                    "\"C:/Users/yyy/Desktop/xampl.bib\" \"ARTICLE | BOOK\" \"Net | Donald Knuth\"\n" +
                    "It's worth noticing that author's first name should be first and it's optional, \n" +
                    "also records types should be all in capital letters.\nThe given .bib file should have" +
                    " all records types in capital letters e.g. ARTICLE as reflection is used.");
            throw new IllegalArgumentException();
        }
        Parser parser = new Parser();
        List<AbstractEntryFactory> entries;
        try {
            entries = parser.parse(args[0]);
            if (!args[1].equals("")) {
                TypeVisitor typeVisitor = new TypeVisitor();
                String[] types = args[1].split("\\|");
                for (int i = 0; i < types.length; i++)
                    types[i] = types[i].trim();
                entries = typeVisitor.visit(types, entries);
            }
            if (!args[2].equals("")) {
                AuthorVisitor surnameVisitor = new AuthorVisitor();
                String[] authors = args[2].split("\\|");
                for (int i = 0; i < authors.length; i++)
                    authors[i] = authors[i].trim();
                entries = surnameVisitor.visit(authors, entries);
            }
            for (AbstractEntryFactory entry : entries)
                System.out.println(entry.getRecord());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
