import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

@SuppressWarnings({"Convert2Lambda", "ClassCanBeRecord"})
public class Processor implements Comparable<Processor> {

    private String name;
    private ClockSpeed clockSpeed;
    private BigDecimal priceEuro;

    public Processor(final String name, final ClockSpeed clockSpeed, final BigDecimal priceEuro) { //Konstruktor lässt nur Werte > 0 zu
        BigDecimal zero = new BigDecimal(0);
        int result = priceEuro.compareTo(zero);
        if (result <= 0){
            throw new ImpossiblePriceException("priceEuro must be greater 0.");
        }
        this.name = name;
        this.clockSpeed = clockSpeed;
        this.priceEuro = priceEuro;
    }

    public static Comparator<Processor> comparatorByClockSpeed() { //Comperator für ClockSpeed
        return Comparator.comparing(Processor::getClockSpeed);
    }

    public static Comparator<Processor> comparatorByPrice() { //Comperator für Preis, aber reversed, da kleiner Preis besser
        return Comparator.comparing(Processor::getPriceEuro).reversed();
    }

    public static Comparator<Processor> comparatorByProcessorRatingScore() { //Comperator für Score, als double
        return Comparator.comparingDouble(Processor::getProcessorRatingScore);
    }

    public ClockSpeed getClockSpeed() { //Getter ClockSpeed
        return this.clockSpeed;
    }

    public BigDecimal getPriceEuro() { //Getter Preis
        return this.priceEuro;
    }
    
    public double getProcessorRatingScore() { //Processor-Rating
        double GHz = this.clockSpeed.getGigaHertz();
        double price = this.priceEuro.doubleValue();
        return Math.pow(GHz, 2.0)/price;
    }

    public String getName() { //Getter für Name
        return this.name;
    }

    @Override
    public int compareTo(Processor o){ //Vergleich zweier Processors
        if (this.clockSpeed.compareTo(o.clockSpeed) == 0){ //Wenn clockSpeed gleich, dann Preis betrachten
            if (this.priceEuro.doubleValue() < o.priceEuro.doubleValue()){
                return 1;
            }
            if (this.priceEuro.doubleValue() > o.priceEuro.doubleValue()){
                return -1;
            } else { //Wenn Preis und ClockSpeed gleich --> 0
                return 0;
            }
        }
        return this.clockSpeed.compareTo(o.clockSpeed); //Wenn ClockSpeed ungleich, dann ClockSpeed vergleichen
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ //Wenn ein und das selbe --> true
            return true;
        }
        if (o == null || !(o instanceof Processor)){ //Wenn null oder kein Processor --> false
            return false;
        }
        Processor other = (Processor) o; //Typecast, um Felder zu vergleichen
        return (this.name.equals(other.name) && //Wenn alle drei gleich --> true sonst false
                this.priceEuro.equals(other.priceEuro) &&
                this.clockSpeed.equals(other.clockSpeed));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priceEuro, clockSpeed);
    }

    @Override
    public String toString() {
        return "Prozessor " + this.getName() + "kostet " + this.getPriceEuro() + "€ und hat einen ClockSpeed von " +
        this.clockSpeed.getGigaHertz() +  " GigaHertz.";
    }

}
