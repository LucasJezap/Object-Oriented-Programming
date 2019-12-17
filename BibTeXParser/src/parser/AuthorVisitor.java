package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


/**
 * Simple class implementing iRecordVisitor which processes given entries and finds suitable entries (in this
 * case we search by given list of author's first name's and last name's)
 */
public class AuthorVisitor implements iRecordVisitor {

    /**
     * @param filter  list of author's first name's and last name's that we are looking for
     * @param entries list of entries on which we apply our filter
     * @return list of suitable entries (those with right authors)
     */
    public List<AbstractEntryFactory> visit(String[] filter, List<AbstractEntryFactory> entries) {
        List<AbstractEntryFactory> matching = new ArrayList<>();
        HashSet<String> authors = new HashSet<>(Arrays.asList(filter));
        for (AbstractEntryFactory entry : entries) {
            for (int i = 0; i < entry.getAuthorsSurnames().length; i++) {
                if (authors.contains(entry.getAuthorsNames()[i] + " " + entry.getAuthorsSurnames()[i]) ||
                        authors.contains(entry.getAuthorsSurnames()[i]) ||
                        authors.contains(entry.getAuthorsSurnames()[i] + " " + entry.getAuthorsNames()[i]))
                    matching.add(entry);
            }
        }
        return matching;
    }
}