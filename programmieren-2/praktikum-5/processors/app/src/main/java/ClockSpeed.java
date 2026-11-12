@SuppressWarnings("ClassCanBeRecord")
public class ClockSpeed implements Comparable<ClockSpeed> {

    public static ClockSpeed withGigaHertz(final double gigaHertz) { // Methode, um ClockSpeed-Objekt mit gHz zu erstellen
        long hzValue = (long) (gigaHertz * 1_000_000_000);
        return new ClockSpeed(hzValue);
    }

    private long hertz;

    public ClockSpeed(final long hertz) { // Konstruktor
        if (hertz <= 0) {
            throw new ImpossibleClockSpeedException("ClockSpeed must be greater 0.");
        }

        this.hertz = hertz;
    }

    public long getHertz() { //Getter für hertz
        return this.hertz;
    }

    public double getGigaHertz() {  //Getter für Gigahertz
        return (double) this.hertz / 1_000_000_000;
    }

    @Override
    public int compareTo(ClockSpeed o) { //Vergleichmethode --> int als Output
        if (this.hertz < o.hertz) {
            return -1;
        }
        if (this.hertz > o.hertz) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) { //Verlgeichsmethode --> boolean als Output
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof ClockSpeed)) {
            return false;
        }
        ClockSpeed other = (ClockSpeed) o; //Cast um auf Feld zuzugreifen
        return this.hertz == other.hertz;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(hertz);
    }

    @Override
    public String toString() {
        return "ClockSpeed-Objekt mit Hertz-Wert: " + this.hertz;
    }
}
