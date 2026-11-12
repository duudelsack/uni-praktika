package sirius;

public abstract class Opponent extends Actor {

    public Opponent(double x ) { //Super-Klasse für alle Gegner-Actors mit Konstrukor. 
        super(x, 0);           //Alle Gegner werden auf einer festen Höhe gespawnt.
    }

}
