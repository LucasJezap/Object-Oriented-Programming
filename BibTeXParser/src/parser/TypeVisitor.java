package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Simple class implementing iRecordVisitor which processes given entries and finds suitable entries (in this
 * case we search by given list of record types)
 */
public class TypeVisitor implements iRecordVisitor {

    /**
     * @param filter  list of record types that we are looking for
     * @param entries list of entries on which we apply our filter
     * @return list of suitable entries (those with right record types)
     */
    public List<AbstractEntryFactory> visit(String[] filter, List<AbstractEntryFactory> entries) {
        List<AbstractEntryFactory> matching = new ArrayList<>();
        HashSet<String> types = new HashSet<>(Arrays.asList(filter));
        for (AbstractEntryFactory entry : entries) {
            if (types.contains(entry.getType()))
                matching.add(entry);
        }
        return matching;
    }
}

