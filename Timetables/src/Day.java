public enum Day {
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT,
    SUN;

    public String toString() {
        switch (this) {
            case MON:
                return "Poniedziałek";
            case TUE:
                return "Wtorek";
            case WED:
                return "Środa";
            case THU:
                return "Czwartek";
            case FRI:
                return "Piątek";
            case SAT:
                return "Sobota";
            case SUN:
                return "Niedziela";
        }
        return "BAAAAD";
    }

    public Day nextDay() {
        switch (this) {
            case MON:
                return TUE;
            case TUE:
                return WED;
            case WED:
                return THU;
            case THU:
                return FRI;
            case FRI:
                return SAT;
            case SAT:
                return SUN;
            case SUN:
                return MON;
        }
        return this;
    }

    public Day prevDay() {
        switch (this) {
            case MON:
                return SUN;
            case TUE:
                return MON;
            case WED:
                return TUE;
            case THU:
                return WED;
            case FRI:
                return THU;
            case SAT:
                return FRI;
            case SUN:
                return SAT;
        }
        return this;
    }
}
