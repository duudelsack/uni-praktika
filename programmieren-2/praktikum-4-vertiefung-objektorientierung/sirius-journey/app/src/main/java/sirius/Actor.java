package sirius;

public abstract class Actor {

    double x; //Feld x-Koordinate
    double y; //Feld y-Koordinate

    public Actor(double x, double y){ //Actor-Konstruktor. Falls y unpassenden Wert hat, wird Fehler geworfen
        if (y < 0 || y > SiriusGUI.WIDTH){
            throw new IllegalArgumentException("Der Y-Wert muss zwischen 0 und" + SiriusGUI.WIDTH + "liegen.");
        }
        this.x = x;
        this.y = y;
    }

    protected abstract String getImage(); //Abstrakte Methode um passende Grafik zu setzen

    protected abstract void update(double time, double deltaTime); //Abstrakte Methode, aktualisiert Zustand von Actor. 
                                                                   //Time = Zeit seit Spielstart, deltaTime = Zeit seit letztem update-Aufruf.
    protected double getX() { //Getter-Methode für X-Wert
        return x;
    }

    protected double getY() { //Getter-Methode für Y-Wert
        return y;
    }

    protected void setX(double x) { //Setter-Methode für X-Wert
        this.x = x;
    }

    protected void setY(double y) { //Setter-Methode für Y-Wert
        this.y = y;
    }

    protected void changeX(double delta) { //Methode ändert X-Wert, indem delta addiert wird
        x += delta;
    }

    protected void changeY(double delta) { //Methode ändert Y-Wert, indem delta addiert wird
        y += delta;
    }
}
