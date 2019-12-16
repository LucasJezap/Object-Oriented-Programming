package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple implementation of BibTeX parser. To simplify things the parser assume that every record is
 * limited by pair of braces ({...}), every string value of attribute is limited by pair of quotes ("...") and
 * that every attribute is separated by comma sing (,). The parser includes @STRING declarations and omits
 * everything different from @STRING declarations and records. The parser ascertain that every required attribute is
 * present in the entry, otherwise it throws exception.
 */
public class Parser {

    /**
     * @param filePath a path to the file that we want to parse
     * @return list of parsed entries
     * @throws java.io.IOException when the input records are not valid
     */
    public List<AbstractEntryFactory> parse(String filePath) throws java.io.IOException {
        BufferedReader fileReader = null;
        StringBuilder result = new StringBuilder();
        HashMap<String, String> substitutedWords = new HashMap<>();
        List<AbstractEntryFactory> entries = new ArrayList<>();
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            LineMatcher lm = new LineMatcher();
            String line = fileReader.readLine();
            while (line != null) {
                if (lm.doStringMatches(line)) {
                    StringBuilder name = new StringBuilder();
                    StringBuilder substitution = new StringBuilder();
                    lm.getMatcher2(line, name, substitution);
                    substitutedWords.put(name.toString(), substitution.toString());
                } else if (lm.doRecordMatches(line)) {
                    StringBuilder type = new StringBuilder();
                    StringBuilder subtype = new StringBuilder();
                    lm.getMatcher1(line, type, subtype);
                    try {
                        Class c = Class.forName("parser." + type.charAt(0) + type.substring(1).toLowerCase() + "EntryFactory");
                        Constructor<?> ct = c.getConstructor();
                        Object entry = ct.newInstance();
                        ((AbstractEntryFactory) entry).initEntry();
                        String x = line;
                        while (!lm.doRecordEndMatches(line)) {
                            line = fileReader.readLine();
                            x += "\n" + line;
                        }
                        ((AbstractEntryFactory) entry).fillEntry(x, substitutedWords);
                        if (((AbstractEntryFactory) entry).checkRequired()) {
                            entries.add((AbstractEntryFactory) entry);
                            result.append(((AbstractEntryFactory) entry).getRecord());
                        } else {
                            throw new IllegalArgumentException("The record " + ((AbstractEntryFactory) entry).getSubtype() + " didn't have " +
                                    "all required attributes");
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                            InvocationTargetException e) {
                        line = fileReader.readLine();
                        continue;
                    }
                }
                line = fileReader.readLine();
            }
        } catch (IOException e) {
            if (fileReader != null)
                fileReader.close();

        }
        return entries;
    }
}
