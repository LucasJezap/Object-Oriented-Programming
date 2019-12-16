public enum Action {
    DAY_EARLIER,
    DAY_LATER,
    TIME_EARLIER,
    TIME_LATER;

    public static void makeChanges(Lesson lesson, Action[] changes) {
        for (Action change : changes) {
            switch (change) {
                case DAY_EARLIER: {
                    if (!lesson.earlierDay()) System.out.println("Can't move lesson to earlier day");
                    else System.out.println(lesson);
                    break;
                }
                case DAY_LATER: {
                    if (!lesson.laterDay()) System.out.println("Can't move lesson to later day");
                    else System.out.println(lesson);
                    break;
                }
                case TIME_EARLIER: {
                    if (!lesson.earlierTime()) System.out.println("Can't move lesson to earlier time");
                    else System.out.println(lesson);
                    break;
                }
                case TIME_LATER: {
                    if (!lesson.laterTime()) System.out.println("Can't move lesson to later time");
                    else System.out.println(lesson);
                    break;
                }
            }
        }
    }
}
