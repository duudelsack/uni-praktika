public enum NoteValue {
    WHOLE("W"),
    HALF("H"),
    QUARTER("Q");

    private final String abbreviation;

    private NoteValue(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static NoteValue parse(String input) {
        for (NoteValue value : values()) {
            if (value.getAbbreviation().equals(input)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Ungültiger Notenwert: " + input);
    }
}
