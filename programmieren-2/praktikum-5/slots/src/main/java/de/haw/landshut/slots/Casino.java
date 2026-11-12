package de.haw.landshut.slots;

import java.util.*;

public class Casino {

    private final List<SlotMachine> slotMachines;

    public Casino(final List<SlotMachine> slotMachines) { //Konstruktor lässt nur Liste mit gültigen Werten zu
        if (slotMachines == null || slotMachines.size() == 0){
            throw new IllegalArgumentException("Die übergebene Liste muss Elemente beinhalten.");
        }
        for (SlotMachine machine : slotMachines){
            if (machine == null){
                throw new IllegalArgumentException("Kein Element der Liste darf null sein.");
            }
        }
        this.slotMachines = new ArrayList<>(slotMachines);
    }

    public SlotMachine findHighestWinningChance() { //Ermitteln, welche Maschine die höchsten Chancen hat.
        SlotMachine highestChance = this.slotMachines.get(0);
        for (SlotMachine machine : this.slotMachines){
            if (machine.getWinningChance() > highestChance.getWinningChance()){
                highestChance = machine;
            }
        }
        return highestChance;
    }

    public Collection<String> getAllOptions() { //Alle Options im Casino, ohne Duplikate
        HashSet<String> allOptions = new HashSet<>(); //HashSet verhindert Duplikate
        for (SlotMachine machine : slotMachines){
            for (String option : machine.getOptions()){
                allOptions.add(option);
            }
        }
        return allOptions;
    }

    public Map<String, List<SlotMachine>> groupByOptions() { //Erstellt Übersicht aller Maschinen nach Option geordnet
        Map<String, List<SlotMachine>> optionDict = new HashMap<>(); //Key-Value-Prinzip
        for (SlotMachine machine : slotMachines){
            for (String option : machine.getOptions()){
                if (!optionDict.containsKey(option)){
                    optionDict.put(option, new ArrayList<>());
                }
                optionDict.get(option).add(machine); //Erst Zugriff auf Value, dann Machine zu Liste hinzufügen
            }
        }
        return optionDict;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o){ //Falls ein und das selbe Casino --> true
            return true;
        }
        if (o == null || !(o instanceof Casino)){ //Falls o null oder kein Casino --> false
            return false;
        }
        Casino other = (Casino) o; //Typecast, um Attribute abzufragen
        return this.slotMachines.equals(other.slotMachines);
    }

    @Override
    public int hashCode() {
        return slotMachines.hashCode();
    }

    @Override
    public String toString() {  //Auflistung aller SlotMachines mit Atrributen.
        String info = "Casino \n";
        for (SlotMachine machine : slotMachines){
            info += machine.toString() + "\n";
        }
        return info;
    }
}
