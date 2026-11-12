public enum NoteName {
    A("A"),
    A_SHARP("A#"),
    B("B"),
    C("C"),
    C_SHARP("C#"),
    D("D"),
    D_SHARP("D#"),
    E("E"),
    F("F"),
    F_SHARP("F#"),
    G("G"),
    G_SHARP("G#");

    private final String abbreviation;

    private NoteName(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static NoteName parse(String input) {
        for (NoteName noteName : values()) {
            if (noteName.getAbbreviation().equals(input)) {
                return noteName;
            }
        }
        throw new IllegalArgumentException("Ungültiger Notenwert: " + input);
    }
}


