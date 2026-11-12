import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Main {

    public static void main(final String[] args) {
        final var core0 = new Processor("Schlimmtel 8", ClockSpeed.withGigaHertz(3.8), new BigDecimal(600));
        final var core1 = new Processor("Schlimmtel 9", ClockSpeed.withGigaHertz(4.2), new BigDecimal(800));
        final var core2 = new Processor("Schlimmtel 7", ClockSpeed.withGigaHertz(3.6), new BigDecimal(300));
        final var core3 = new Processor("MAD Reisn 7", ClockSpeed.withGigaHertz(3.1), new BigDecimal(350));
        final var core4 = new Processor("MAD Reisn 8", ClockSpeed.withGigaHertz(3.1), new BigDecimal(550));
        final var core5 = new Processor("MAD Reisn 9", ClockSpeed.withGigaHertz(2.8), new BigDecimal(150));
        final var core6 = new Processor("MAD Reisn 10", ClockSpeed.withGigaHertz(1.8), new BigDecimal(150));
        final var core7 = new Processor("MAD Reisn 8.1", ClockSpeed.withGigaHertz(2.8), new BigDecimal(400));
        final var core8 = new Processor("Pear N2", ClockSpeed.withGigaHertz(4.3), new BigDecimal(1200));
        final var core9 = new Processor("Pear N3 Mini", ClockSpeed.withGigaHertz(1.7), new BigDecimal(5000));

        List<Processor> list = new ArrayList<>(); //Liste erstellen & Processors hinzufügen
        list.add(core0);
        list.add(core1);
        list.add(core2);
        list.add(core3);
        list.add(core4);
        list.add(core5);
        list.add(core6);
        list.add(core7);
        list.add(core8);
        list.add(core9);

        int vergleich01 = core0.compareTo(core1); //Vergleich mit compareTo()
        System.out.println("Natürlicher Vergleich: Schlimmtel 8 verglichen mit Schlimmtel 9 liefert den Wert " + vergleich01);
        System.out.println("");

        Comparator<Processor> clockSpeedComparison = Processor.comparatorByClockSpeed(); //Vergleiche mit comparator
        int clockSpeed01 = clockSpeedComparison.compare(core0, core1);
        System.out.println("Comparator-Vergleich: Schlimmtel 8 verglichen mit Schlimmtel 9 liefert den Wert " + clockSpeed01);
        System.out.println("");

        Comparator<Processor> priceComparison = Processor.comparatorByPrice();
        int price01 = priceComparison.compare(core0, core1);
        System.out.println("Preis-Vergleich: Schlimmtel 8 verglichen mit Schlimmtel 9 liefert den Wert " + price01);
        System.out.println("");

        Comparator<Processor> processorRatingComparison = Processor.comparatorByProcessorRatingScore();
        int rating01 = processorRatingComparison.compare(core0, core1);
        System.out.println("Rating-Vergleich: Schlimmtel 8 verglichen mit Schlimmtel 9 liefert den Wert " + rating01);
        System.out.println("");

        Collections.sort(list); //Nach nat. Ordnung sortierte Liste
        System.out.println("Sortiert nach natürlicher Ordnung: " + list);
        System.out.println("");

        Collections.sort(list, Processor.comparatorByPrice()); //Nach comparator-Ordnung sortiere Listen
        System.out.println("Sortiert nach Preis: " + list);
        System.out.println("");

        Collections.sort(list, Processor.comparatorByClockSpeed());
        System.out.println(("Sortiert nach ClockSpeed: ") + list);
        System.out.println("");

        Collections.sort(list, Processor.comparatorByProcessorRatingScore());
        System.out.println("Sortiert nach Rating: " + list);
    }

}
