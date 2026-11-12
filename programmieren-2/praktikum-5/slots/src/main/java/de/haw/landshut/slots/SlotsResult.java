package de.haw.landshut.slots;

import java.util.*;

public class SlotsResult {

    private final List<String> slots;

    public SlotsResult(final List<String> slots) { 
        this.slots = new ArrayList<>(slots);
    }

    public boolean isWin() { //Wenn alle Einträge gleich --> true, sonst false
        String compare = getSlots().get(0);
        for (int i = 1; i < getSlots().size(); i++){
            if (!getSlots().get(i).equals(compare)){
                return false;
            } 
        }
        return true;
    }

    public List<String> getSlots() { //Methode returnt Kopie von Slots
        List<String> copy = new ArrayList<>(this.slots);
        return copy;
    }

    @Override
    public boolean equals(final Object o) { //Methode prüft auf Gleichheit
        if (this == o){ //Wenn ein und das selbe Objekt --> true
            return true;
        }
        if (o == null || !(o instanceof SlotsResult)){ //Wenn Objekt b null oder keine Instanz SlotsResult --> false
            return false;
        }
        SlotsResult other = (SlotsResult) o; //Typecast, um Methoden anzuwenden
        if (this.getSlots().size() != other.getSlots().size()){ //Wenn Länge ungleich --> false
            return false;
        }
        for (int i = 0; i < this.getSlots().size(); i++){ //Wenn zwei Einträge ungleich --> false
            if (!this.getSlots().get(i).equals(other.getSlots().get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slots);
    }

    @Override
    public String toString() {
        return "SlotsResult: " + slots.toString();

    }
}