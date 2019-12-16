package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple class which main task is to calculate regular expressions and use them. This class was made so the patterns
 * are compiled only once but used many times (big time of compilation). It's mainly used to distinguish type of line
 * read by parser.
 */
public class LineMatcher {
    /**
     * Attributes:<br>
     * - pattern1: a pattern which helps to find the record type and subtype,<br>
     * - pattern2: a pattern which helps to find the @STRING substituted word and substitution word,<br>
     * - stringPattern: a pattern which helps to tell if the line is a @STRING line,<br>
     * - recordPattern: a pattern which helps to tell if the line is a @RECORD line,<br>
     * - endRecordPattern: a pattern which helps to tell if the line is the end of the record<br>
     * - matcher1: a matcher connected with pattern1<br>
     * - matcher2: a matcher connected with pattern2<br>
     */
    public Pattern pattern1;
    public Pattern pattern2;
    public Pattern stringPattern;
    public Pattern recordPattern;
    public Pattern endRecordPattern;
    public Matcher matcher1;
    public Matcher matcher2;

    /**
     * A constructor
     */
    public LineMatcher() {
        pattern1 = Pattern.compile("@(\\w+)\\{(.+),");
        pattern2 = Pattern.compile("@STRING\\{(.+)\\s+=\\s+\"(.+)\"}");
        stringPattern = Pattern.compile("@.*}\\s*");
        recordPattern = Pattern.compile("\\s*@.*,\\s*");
        endRecordPattern = Pattern.compile("}\\s*");
    }

    /**
     * @param match   the string we apply pattern to
     * @param type    the type of record we want to find
     * @param subtype the subtype of record we want to find
     */
    public void getMatcher1(String match, StringBuilder type, StringBuilder subtype) {
        matcher1 = pattern1.matcher(match);
        if (matcher1.matches()) {
            type.append(matcher1.group(1));
            subtype.append(matcher1.group(2));
        }
    }

    /**
     * @param match the string we apply pattern to
     * @param type  the type of attribute we want to find
     * @param value the value of the attribute we want to find
     */
    public void getMatcher2(String match, StringBuilder type, StringBuilder value) {
        matcher2 = pattern2.matcher(match);
        if (matcher2.matches()) {
            type.append(matcher2.group(1));
            value.append(matcher2.group(2));
        }
    }

    /**
     * @param match the string we want to know if matches stringPattern
     * @return true if the string matches stringPattern
     */
    public boolean doStringMatches(String match) {
        return stringPattern.matcher(match).matches();
    }

    /**
     * @param match the string we want to know if matches recordPattern
     * @return true if the stirng matches recordPattern
     */
    public boolean doRecordMatches(String match) {
        return recordPattern.matcher(match).matches();
    }

    /**
     * @param match the string we want to know if matches endRecordPattern
     * @return true if the string matches endRecordPattern
     */
    public boolean doRecordEndMatches(String match) {
        return endRecordPattern.matcher(match).matches();
    }
}
