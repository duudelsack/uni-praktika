package sirius;

public class OpponentChaser extends Opponent {

    protected String getImage() { // Code, um richtige Grafik anzuzeigen
        return "opponent-chaser";
    }

    Player player; // Attribut player

    public OpponentChaser(Player player, double x) { // Konstruktor gibt x-Wert weiter und besetzt das Feld player mit
                                                     // der Spieler-Instanz
        super(x);
        this.player = player;
    }

    protected void update(double time, double deltaTime) { // Regelt Verhalten vom Chaser, damit dieser auf den Spieler
                                                           // zukommt.
        if (player.getX() < this.getX()) {
            this.x -= 75 * deltaTime;
        } else if (player.getX() > this.getX()) {
            this.x += 75 * deltaTime;
        }
        this.y += 150 * deltaTime;
    }

}
