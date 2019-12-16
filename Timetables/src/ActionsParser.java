public class ActionsParser {
    public static Action[] parse(String[] arr) {
        Action[] arr2 = new Action[arr.length];
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case "d-": {
                    arr2[i] = Action.DAY_EARLIER;
                    break;
                }
                case "d+": {
                    arr2[i] = Action.DAY_LATER;
                    break;
                }
                case "t-": {
                    arr2[i] = Action.TIME_EARLIER;
                    break;
                }
                case "t+": {
                    arr2[i] = Action.TIME_LATER;
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Translation " + arr[i] + " is incorrect!!!");
                }
            }
        }
        return arr2;
    }

    public String[] repair(String[] args) {
        int i = 0;
        for (String arg : args) {
            if (arg.equals("d+") || arg.equals("d-") || arg.equals("t+") || arg.equals("t-"))
                i += 1;
        }
        String[] repaired = new String[i];
        i = 0;
        for (String arg : args) {
            if (arg.equals("d+") || arg.equals("d-") || arg.equals("t+") || arg.equals("t-")) {
                repaired[i] = arg;
                i += 1;
            }
        }
        return repaired;
    }
}
