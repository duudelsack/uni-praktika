public enum Entry {
    S_UNSCORED('S', false),
    O_UNSCORED('O', false),
    S_SCORED('$', true),
    O_SCORED('Ø', true);

    private final char display;
    private final boolean scored;

    Entry(char display, boolean scored) {
        this.display = display;
        this.scored = scored;
    }

    public char getDisplay() {
        return display;
    }

    public boolean isScored() {
        return scored;
    }

    public static Entry fromDisplay(char display) {
        for (Entry entry : Entry.values()) {
            if (entry.display == display) {
                return entry;
            }
        }
        throw new IllegalArgumentException("Ungültiges Zeichen: " + display);
    }

    public Entry toScored() {
        switch (this) {
            case S_UNSCORED: return S_SCORED;
            case O_UNSCORED: return O_SCORED;
            default: return this;
        }
    }

    public Entry toUnscored() {
        switch (this) {
            case S_SCORED: return S_UNSCORED;
            case O_SCORED: return O_UNSCORED;
            default: return this;
        }
    }
}

