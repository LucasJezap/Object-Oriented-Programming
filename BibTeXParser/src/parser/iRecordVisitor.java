package parser;

import java.util.List;

/**
 * Simple interface for Visitors filtering entries. It's a main part of Visitor design pattern.
 * Filters should be either type of record or particular attribute like author or title.
 * There's only one method visit which is given specific filter, list of entries and returns
 * suitable entries depending on the filter
 */
public interface iRecordVisitor {
    List<AbstractEntryFactory> visit (String[] filter, List<AbstractEntryFactory> entries);
}
