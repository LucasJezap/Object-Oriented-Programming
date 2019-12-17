package parser;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This abstract class is the most important part of the parsing process. Here the given entry
 * is being adapted to HashMaps of required and optional Attributes. Every entry also gets bonus variables
 * depending on designed Visitors so that every Visitor class is easy and readable. Such manner makes it easy
 * to add new Visitors just by adding appropriate variable to each entry.
 */
public abstract class AbstractEntryFactory {
    /**
     * Attributes:<br>
     * - requiredAttributes: a HashMap of requiredAttributes of the entry,<br>
     * - optionalAttributes: a HashMap of optionalAttributes of the entry,<br>
     * - type: a type of the entry e.g. ARTICLE,<br>
     * - subtype: a subtype of the entry e.g. article-full,<br>
     * - authorsSurnames: an array of author's last names:,<br>
     * - authorsNames: an array of author's first names:,<br>
     */
    private HashMap<String, String> requiredAttributes = new HashMap<>();
    private HashMap<String, String> optionalAttributes = new HashMap<>();
    private String type;
    private String subtype;
    private String[] authorsSurnames;
    private String[] authorsNames;

    /**
     * an abstract method that initializes entry depending of it's type
     */
    public abstract void initEntry();


    /**
     * @param type a type of attribute
     * @param line a value of the attribute
     * @return fixed value of the attribute (removed unnecessary characters)
     */
    public String fixLine(String type, String line) {
        line = line.replaceAll("[{}\"$!=~\\\\']", "");
        line = line.replace("[", "");
        line = line.replace("]", "");
        line = line.replaceAll("\\s+"," ");
        if (type.equals("journal"))
            line = line.replace("mbox", "");
        if (type.equals("year")) {
            line = line.replaceAll("noopsort[0-9]{4}[a-z]{1}", "");
            line = line.replaceAll("switchargs--[0-9]{2}", "");
        }
        return line;
    }

    /**
     * @param entry            a string of the entry that we want to convert
     * @param substitutedWords a HashMap of words that should be replaced
     */
    public void fillEntry(String entry, HashMap<String, String> substitutedWords, List<AbstractEntryFactory> entries) {
        String[] lines = entry.split((",[\\n\\r]"));
        Pattern pattern1 = Pattern.compile("@(\\w+)\\{(.+)");
        Matcher matcher1 = pattern1.matcher(lines[0]);
        matcher1.matches();
        this.type = matcher1.group(1);
        this.subtype = matcher1.group(2);
        Pattern pattern2 = Pattern.compile("\\s*(\\w*)\\s*=\\s*(.*)\\n?\\s*?(.*)?");
        Pattern pattern3 = Pattern.compile("[{\"].+[\"}]");
        for (int i = 1; i < lines.length - 1; i++) {
            Matcher matcher2 = pattern2.matcher(lines[i]);
            matcher2.matches();
            String attribute = matcher2.group(1);
            String value = (matcher2.group(3) != null) ? matcher2.group(2) + matcher2.group(3) : matcher2.group(2);
            if (attribute.equals("editor") && requiredAttributes.containsKey("author") &&
                    !optionalAttributes.containsKey("editor"))
                attribute = "author";
            if (!this.type.equals("ARTICLE") && attribute.equals("number"))
                attribute = "volume";
            if (this.type.equals("INBOOK") && attribute.equals("pages"))
                attribute = "chapter";

            /* DOING STRING CONCATENATION AND REPLACEMENT */
            StringBuilder tmpValue = new StringBuilder();
            String[] values = value.split(" # ");
            for (String val : values) {
                if (!pattern3.matcher(val).matches() && substitutedWords.containsKey(val))
                    val = substitutedWords.get(val);
                tmpValue.append(val);
            }
            value = tmpValue.toString();

            value = fixLine(attribute, value);

            /* CROSSREF */
            if (attribute.equals("crossref")) {
                AbstractEntryFactory crossrefEntry = new AbstractEntryFactory() {
                    @Override
                    public void initEntry() {
                    }
                };
                for (AbstractEntryFactory entryx : entries) {
                    if (entryx.getSubtype().equals(value.toLowerCase())) {
                        crossrefEntry = entryx;
                        break;
                    }
                }
                for (String attributex: crossrefEntry.getRequiredAttributes().keySet()) {
                    String valuex = crossrefEntry.getRequiredAttributes().get(attributex);
                    if (attributex.equals("author")) {
                        this.authorsSurnames = new String[valuex.split("and", -1).length];
                        this.authorsNames = new String[this.authorsSurnames.length];
                        String[] auth = valuex.split(" and ");
                        for (int j = 0; j < auth.length; j++) {
                            this.authorsNames[j] = auth[j].split(" ", 2)[0];
                            this.authorsSurnames[j] = auth[j].split(" ")[auth[j].split(" ").length - 1];
                        }
                    }
                    if (requiredAttributes.containsKey(attributex))
                        requiredAttributes.replace(attributex,valuex);
                    else if (optionalAttributes.containsKey(attributex))
                        optionalAttributes.replace(attributex,valuex);
                }
                for (String attributex: crossrefEntry.getOptionalAttributes().keySet()) {
                    String valuex = crossrefEntry.getOptionalAttributes().get(attributex);
                    if (requiredAttributes.containsKey(attributex))
                        requiredAttributes.replace(attributex,valuex);
                    else if (optionalAttributes.containsKey(attributex))
                        optionalAttributes.replace(attributex,valuex);
                }
                continue;
            }

            if (attribute.equals("author")) {
                this.authorsSurnames = new String[value.split("and", -1).length];
                this.authorsNames = new String[this.authorsSurnames.length];
                String[] auth = value.split(" and ");
                for (int j = 0; j < auth.length; j++) {
                    this.authorsNames[j] = auth[j].split(" ", 2)[0];
                    this.authorsSurnames[j] = auth[j].split(" ")[auth[j].split(" ").length - 1];
                }
            }
            if (requiredAttributes.containsKey(attribute))
                requiredAttributes.replace(attribute, value);
            else if (optionalAttributes.containsKey(attribute))
                optionalAttributes.replace(attribute, value);
        }
    }

    /**
     * @return true if the entry is valid (it have all required attributes)
     */
    public boolean checkRequired() {
        for (String key : requiredAttributes.keySet())
            if (requiredAttributes.get(key).equals("")) {
                System.out.println(key);
                return false;
            }
        return true;
    }

    /**
     * @param result built string of output
     * @return same string but with line of dashes
     */
    private String addDashes(String result) {
        for (int i = 0; i < 103; i++)
            result += "-";
        return result;
    }

    /**
     * @return a record in an object-oriented form (a table with ASCII character box)
     */
    public String getRecord() {
        String result = "";
        result = addDashes(result);
        result += String.format("\n|%1$-101s|\n", "   " + type + "(" + subtype + ")");
        result = addDashes(result);
        for (String key : requiredAttributes.keySet()) {
            if (key.equals("author") && this.authorsNames.length > 1) {
                String[] authors = requiredAttributes.get(key).split(" and ");
                for (int i = 0; i < authors.length; i++) {
                    result += (i == 0) ? String.format("\n|%1$-20s|", "   " + key) : String.format("\n|%20s|", "");
                    result += String.format("%1$-80s|", "   " + authors[i]);
                }
                result += "\n";
                result = addDashes(result);
            } else {
                result += String.format("\n|%1$-20s|", "   " + key);
                result += String.format("%1$-80s|\n", "   " + requiredAttributes.get(key));
                result = addDashes(result);
            }
        }
        for (String key : optionalAttributes.keySet()) {
            if (!optionalAttributes.get(key).equals("")) {
                if (key.equals("author") && this.authorsNames.length > 1 ||
                        key.equals("editor") && optionalAttributes.get(key).split(" and ").length > 1) {
                    String[] authors = optionalAttributes.get(key).split(" and ");
                    for (int i = 0; i < authors.length; i++) {
                        result += (i == 0) ? String.format("\n|%1$-20s|", "   " + key) : String.format("\n|%20s|", "");
                        result += String.format("%1$-80s|", "   " + authors[i]);
                    }
                    result += "\n";
                    result = addDashes(result);
                } else {
                    result += String.format("\n|%1$-20s|", "   " + key);
                    result += String.format("%1$-80s|\n", "   " + optionalAttributes.get(key));
                    result = addDashes(result);
                }
            }
        }
        result += "\n";
        return result;
    }

    /**
     * @return requiredAttributes
     */
    public HashMap<String, String> getRequiredAttributes() {
        return requiredAttributes;
    }

    /**
     * @return optionalAttributes
     */
    public HashMap<String, String> getOptionalAttributes() {
        return optionalAttributes;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @return subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * @return list of authors surnames or empty array if there aren't any authors
     */
    public String[] getAuthorsSurnames() {
        if (this.authorsSurnames != null)
            return authorsSurnames;
        return new String[0];
    }

    /**
     * @return list of authors names or empty array if there aren't any authors
     */
    public String[] getAuthorsNames() {
        if (this.authorsNames != null)
            return authorsNames;
        return new String[0];
    }

}
