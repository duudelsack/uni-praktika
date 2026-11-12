package zinsen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ZinsenBerechner implements Iterable<Double> {

    private double startKapital; // Felder
    private double zielKapital;
    private double zinssatz;

    public ZinsenBerechner(double startKapital, double zielKapital, double zinssatz) { // Konstruktor erlaubt nur bestimmte Werte
        if (startKapital <= 0) {
            throw new IllegalArgumentException("startKapital muss größer als 0 sein.");
        }
        if (zielKapital <= startKapital) {
            throw new IllegalArgumentException("zielKapital muss größer als Startkapital sein.");
        }
        if (zinssatz <= 0 || zinssatz >= 100.0) {
            throw new IllegalArgumentException("Der zinssatz muss zwischen 0 und 100 liegen.");
        }

        this.startKapital = startKapital; // Konstruktor belegt Felder
        this.zielKapital = zielKapital;
        this.zinssatz = zinssatz;
    }

    private class ZinsenIterator implements Iterator<Double> { //Innere Klasse

        private double aktuell;

        @Override
        public boolean hasNext() { //true, falls aktueller Wert kleiner als Zielwert
            return aktuell < zielKapital;
        }

        @Override
        public Double next() { //Berechnet Wert der nächsten Zinsperiode
            if (hasNext()) {
                double before = aktuell;
                aktuell = aktuell + aktuell * zinssatz / 100;
                return before;
            } else {
                throw new NoSuchElementException();
            }
        }

        public ZinsenIterator() {
            aktuell = startKapital;
        }
    }

    @Override
    public Iterator<Double> iterator() {
        return new ZinsenIterator();
    }

    public static void main(String[] args) {
        int counter = 0;
        ZinsenBerechner zb = new ZinsenBerechner(100, 1000, 3); //Iterable-Instanz erstellen
        Iterator<Double> iterator = zb.iterator(); //Iterator von Iterable 

        while (iterator.hasNext()) {
            System.out.printf("%d. Periode: %f%n", counter, iterator.next());
            counter += 1;
        }
    }

}