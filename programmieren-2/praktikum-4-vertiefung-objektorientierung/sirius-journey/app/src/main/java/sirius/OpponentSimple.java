package sirius;

public class OpponentSimple extends Opponent {

    protected OpponentSimple(double x) { //Konstruktor für OpponentSimple, nur x-Koordinate variiert. 
        super(x);
    }

    protected String getImage() { //Entsprechende Grafik für Gegner-Typen auswählen.
        return "opponent-simple";
    }

    protected void update(double time, double deltaTime) { //Methode beeinflusst die Bewegung von Gegner
        y += 120 * deltaTime;
    }

}
