package de.haw.landshut;

import java.util.*;

import de.haw.landshut.slots.Casino;
import de.haw.landshut.slots.SlotMachine;
import de.haw.landshut.slots.SlotsResult;

public class Main {
    
        public static void main(String[] args){
            SlotMachine A = new SlotMachine(1337,5 , "Eichel","Graß","Herz","Schellen");
            SlotMachine B = new SlotMachine(1337, 6, "Eichel","Graß","Herz");
            SlotMachine C = new SlotMachine(1337, 4, "A","B","C","D","E");
            SlotMachine D = new SlotMachine(1337, 4, "A","B","C","D","E","D");

            int counter = 0;
            boolean win = false;
            while (!win){
                counter += 1;
                SlotsResult result = A.play();
                System.out.printf("%d. Durchgang:\n", counter);
                System.out.println(result);
                win = result.isWin();
            }

            List<SlotMachine> machines = new ArrayList<>();
            machines.add(A);
            machines.add(B);
            machines.add(C);
            machines.add(D);
            Casino casino = new Casino(machines);

            SlotMachine bestChance = casino.findHighestWinningChance();
            double chance = bestChance.getWinningChance()*100;
            System.out.printf("Die SlotMachine mit der höchsten Gewinnchance ist: " + bestChance + "mit einer Chance von %.2f%%\n", chance);

            System.out.println("Folgende Optionen gibt es im Casino: " + casino.getAllOptions());

            Map<String, List<SlotMachine>> grouped = casino.groupByOptions();
            List<SlotMachine> aList = grouped.get("A");
            System.out.println("Folgende SlotMachines haben die Option A: " + aList);

            try {
                Casino casinoNull = new Casino(null);
            } catch (IllegalArgumentException exception) {
                System.out.println("casinoNull: Ein Casino muss mit einer gültigen Liste instanziiert werden.");
            }

            try {
                List<SlotMachine> emptyList = new ArrayList<>();
                Casino casinoEmpty = new Casino(emptyList);
            } catch (IllegalArgumentException exception) {
                System.out.println("casinoEmpty: Ein Casino muss mit einer befüllten Liste instanziiert werden.");
            }

            try {
                List<SlotMachine> nullList = new ArrayList<>();
                nullList.add(null);
                Casino casinoNone = new Casino(nullList);
            } catch (IllegalArgumentException exception) {
                System.out.println("casinoNone: Die Liste eines Casinos muss Strings enthalten.");
            }
        }
}
