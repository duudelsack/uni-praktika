public class Note {
    private NoteName noteName;
    private NoteValue noteValue;
    private Note nextNote;

    /* Konstruktor initialisiert eine neue Note. nextNote wird Null gesetzt, weil es noch keine nächste Note gibt */
    public Note(NoteName name, NoteValue value) {
        this.noteName = name;
        this.noteValue = value;
        this.nextNote = null;
    }

    /* ein zweiter Konstruktor der die parse(string) Methoden von NoteName und NoteValue konvertiert. Tuft den ersten Konstruktor auf*/
    public Note(String name, String value) {
        this(NoteName.parse(name), NoteValue.parse(value));
    }

    /* die Getter für alle Felder */
    public NoteName getNoteName() {
        return noteName;
    }

    public NoteValue getNoteValue() {
        return noteValue;
    }

    public Note getNextNote() {
        return nextNote;
    }

    /* Setter für nextNoter */
    public void setNextNote(Note nextNote) {
        this.nextNote = nextNote;
    }

    /* vergleicht Noten ob sie gleich sind am Namen und Value. */
    public boolean equals(Object other) {
        if (!(other instanceof Note)) return false;
        Note n = (Note) other;
        return this.noteName == n.noteName && this.noteValue == n.noteValue;
    }

    /* wandelt die Note in einen menschen lesbaren string um */
    public String toString() {
        return noteName.getAbbreviation() + "," + noteValue.getAbbreviation();
    }

    /* berechnet die Frequenz einer Note mithilfe einer Formel. 
     * h = Halbtonabstand zur Note A
    */
    public double getPitch() {
        int h = getHalfStepsFromA(noteName);
        return Math.pow(2, h / 12.0) * 440;
    }

    private int getHalfStepsFromA(NoteName name) {
        switch (name) {
            case A: return 0;
            case A_SHARP: return 1;
            case B: return 2;
            case C: return 3;
            case C_SHARP: return 4;
            case D: return 5;
            case D_SHARP: return 6;
            case E: return 7;
            case F: return 8;
            case F_SHARP: return 9;
            case G: return 10;
            case G_SHARP: return 11;
            default: throw new IllegalArgumentException("Unbekannte Note: " + name);
        }
    }
}

