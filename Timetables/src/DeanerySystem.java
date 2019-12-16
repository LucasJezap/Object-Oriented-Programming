import java.sql.Time;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DeanerySystem {
    public static void main(String[] args) {
//        Term term1 = new Term(9, 45, Day.MON);
//        System.out.println(term1);                    //Ma się wypisać: "9:45 [90]"
//        Term term2 = new Term(10, 15, Day.TUE);
//        System.out.println(term2);                    //Ma się wypisać: "10:15 [90]"
//        System.out.println(term1.earlierThan(term2)); //Ma się wypisać: "true"
//        System.out.println(term1.equals(term2));      //Ma się wypisać: "false"
//        System.out.println(term1.endTerm(term2));     //Ma się wypisać: "9:45 [30]"
//        System.out.println(term1.endTerm());          //Ma się wypisać: "11:15 [90]"
//        Day day = Day.MON;
//        System.out.println(day.prevDay());
//        System.out.println(day);
//
//        Lesson lesson = new Lesson(new Term(9,35,Day.TUE),"Programowanie w języku Ruby","Stanisław Polak",2);
//        System.out.println(lesson); //Ma się wypisać:
//        //      Programowanie w języku Ruby (Wtorek 9:35-11:05)
//        //      Drugi rok studiów stacjonarnych
//        //      Prowadzący: Stanisław Polak
//        Lesson lesson2 = new Lesson(new Term(14,0,Day.THU),"Programowanie obiektowe","Stanisław Polak",2);
//        System.out.println(lesson2);
//        Action[] changes = ActionsParser.parse(args);
//        for(int i=0; i<args.length; i++) System.out.print(args[i]+" ");
//        System.out.println();
//        Action.makeChanges(lesson2,changes);
        Action[] actions;
        try {
            actions = new ActionsParser().parse(args);
        } catch (IllegalArgumentException exception) {
            actions = new ActionsParser().parse(new ActionsParser().repair(args));
        }
        AbstractTimetable timetable = new Timetable1();
        Lesson l1 = new Lesson(timetable, new Term(8, 0, Day.TUE), "Angielski", "Nowak", 1);
        Lesson l2 = new Lesson(timetable, new Term(9, 30, Day.MON), "JTP", "Kowalski", 3);
        try {
            timetable.put(l1);
        } catch (IllegalArgumentException exception) {
            System.out.println("This termin is busy!!!");
        }
        try {
            timetable.put(l2);
        } catch (IllegalArgumentException exception) {
            System.out.println("This termin is busy!!!");
        }
        try {
            timetable.put(new Lesson(timetable, new Term(15, 30, Day.FRI), "Obiektowe", "Polak", 2));
        } catch (IllegalArgumentException exception) {
            System.out.println("This termin is busy!!!");
        }
        System.out.println(timetable);
        timetable.perform(actions);
        System.out.println();
        System.out.println(timetable);
        BasicTerm b1=new BasicTerm(9, 30, 5);
        BasicTerm b2=new BasicTerm(11, 5, 10);
        Set<Break> breaks = new TreeSet<Break>();                            // MY AGH PLAN FOR THIRD SEMESTER
        breaks.add(new Break(new Term(9, 30, Day.MON, 5)));
        breaks.add(new Break(new Term(11, 5, Day.MON, 10)));
        breaks.add(new Break(new Term(12, 45, Day.MON, 5)));
        breaks.add(new Break(new Term(14, 20, Day.MON, 20)));
        breaks.add(new Break(new Term(16, 10, Day.MON, 5)));
        breaks.add(new Break(new Term(17, 45, Day.MON, 5)));
        breaks.add(new Break(new Term(19, 20, Day.MON, 10)));
        Timetable2 timetable2 = new Timetable2(breaks, true);
//        timetable2.put(new Lesson(timetable2,new Term(8,0,Day.MON),"Fizyka wykład","Kąkol",2));
//        timetable2.put(new Lesson(timetable2,new Term(12,50,Day.MON),"Obiektowe laby","Polak",2));
//        timetable2.put(new Lesson(timetable2,new Term(17,50,Day.MON),"Statystyka laby","Nosek",2));
//        timetable2.put(new Lesson(timetable2,new Term(11,15,Day.TUE),"Statystyka wykład","Smołka",2));
//        timetable2.put(new Lesson(timetable2,new Term(12,50,Day.TUE),"Rurki wykład","Schaefer",2));
//        timetable2.put(new Lesson(timetable2,new Term(14,40,Day.TUE),"Geometryczne wykład","Głut",2));
//        timetable2.put(new Lesson(timetable2,new Term(16,15,Day.TUE),"Grafowe laby","Łoś",2));
//        timetable2.put(new Lesson(timetable2,new Term(17,50,Day.TUE),"Fizyka laby","Armatys",2));
//        timetable2.put(new Lesson(timetable2,new Term(11,15,Day.WED),"Geometryczne laby","Głut",2));
//        timetable2.put(new Lesson(timetable2,new Term(12,50,Day.WED),"Obiektowe wykład","Pohl",2));
//        timetable2.put(new Lesson(timetable2,new Term(14,40,Day.THU),"Funkcyjne wykład","Dębski",2));
//        timetable2.put(new Lesson(timetable2,new Term(16,15,Day.THU),"Grafowe wykład","Meszka",2));
//        timetable2.put(new Lesson(timetable2,new Term(17,50,Day.THU),"Fizyka ćwiczenia","Tokarz",2));
//        timetable2.put(new Lesson(timetable2,new Term(9,35,Day.FRI),"Bazy wykład","Zygmunt",2));
//        timetable2.put(new Lesson(timetable2,new Term(11,15,Day.FRI),"Bazy laby","Zygmunt",2));
//        timetable2.put(new Lesson(timetable2,new Term(12,50,Day.FRI),"Rurki laby","Łoś",2));
//        timetable2.put(new Lesson(timetable2,new Term(14,40,Day.FRI),"Funkcyjne laby","Dębski",2));
        try {
            timetable2.put(new Lesson(timetable2, new Term(12, 50, Day.MON), "Obiektowe laby", "Polak", 2));
        } catch (IllegalArgumentException exception) {
            System.out.println("This termin is busy!!!");
        }
        try {
            timetable2.put(new Lesson(timetable2, new Term(12, 50, Day.WED), "Obiektowe wykład", "Pohl", 2));
        } catch (IllegalArgumentException exception) {
            System.out.println("This termin is busy!!!");
        }
        try {
            timetable2.put(new Lesson(timetable2, new Term(14, 40, Day.THU), "Funkcyjne wykład", "Dębski", 2));
        } catch (IllegalArgumentException exception) {
            System.out.println("This termin is busy!!!");
        }
        System.out.println(timetable2);
        timetable2.perform(actions);                       // TEST IF PERFORM WORKS
        System.out.println();
        System.out.println(timetable2);
        timetable2.accept(new StudentVisitor(1,true,Day.MON));
        timetable2.accept(new TeacherVisitor("Polak",Day.WED));
    }
}