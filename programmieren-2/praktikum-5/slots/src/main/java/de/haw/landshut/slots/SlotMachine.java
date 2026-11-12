package de.haw.landshut.slots;

import java.util.*;

public class SlotMachine {

    private int slotsCount; //Felder
    private List<String> options;
    private Random random;

    public SlotMachine(final int seed, final int slotsCount, final String... options) { //Konstruktor initialisiert nur mit gültigen Parametern
        if (slotsCount <= 0){
            throw new IllegalArgumentException("slotsCount muss größer 0 sein.");
        }
        if (options == null || options.length == 0) {
            throw new IllegalArgumentException("options muss mind. ein Element beinhalten.");
        }
        List<String> uniqueStrings = new ArrayList<>();
        for (String option : options){  //For-Schleife prüft Strings und sortiert doppelte aus.
            if (option == null || option.trim().isEmpty()){
                throw new IllegalArgumentException("options darf keine leeren oder blanken Elemente enthalen.");
            }
            if (!uniqueStrings.contains(option)){
                uniqueStrings.add(option);
            }
        }
        this.slotsCount = slotsCount;
        this.options = uniqueStrings;
        this.random = new Random(seed);
    }

    public SlotsResult play() {
        List<String> rndOptions = new ArrayList<>();
        for (int i = 0; i < slotsCount; i++){ //Für jedes Feld ein zufällige Option wählen
            rndOptions.add(chooseRandom());
        }
        SlotsResult results = new SlotsResult(rndOptions); //Result-Instanz anlegen
        return results; //Ergebnisse returnen.
    }

    public List<String> getOptions() { //Getter für Options.
        return options;
    }

    private String chooseRandom() { //Wählt zufällige option aus options.
        int amount = getAmountOptions();
        int index = random.nextInt(amount);
        return options.get(index);
    }

    public int getAmountOptions() { //Hilfsmethode für chooseRandom.
        return options.size();
    }

    public double getWinningChance() {
        return Math.pow(1/(double) getAmountOptions(), (double) slotsCount); //Gewinnchance durch (1/AnzahlOptionen)^AnzahlSlots
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o){ //Objekt a ist Objekt b --> true
            return true;
        }
        if (o == null || !(o instanceof SlotMachine)){ //Objekt b ist null oder keine SlotMachine --> false
            return false;
        }
        SlotMachine other = (SlotMachine) o; //TypeCast, um .slotsCount/.options anzuwenden
        if (this.slotsCount == other.slotsCount && this.options.equals(other.options)){  //Wenn beides identisch --> true
            return true;
        }
        return false; //falls keine if-Bedingung true ergibt --> false
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotsCount, options);
    }

    @Override
    public String toString() {
        return "slotsCount:" + slotsCount +", Options:" + options; 
    }
}
