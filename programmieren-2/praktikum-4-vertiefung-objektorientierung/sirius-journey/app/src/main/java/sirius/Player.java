package sirius;

public class Player extends Actor implements ProjectileEmitter {

    public Player() { // Wieso ist es nicht möglich, dem Konstruktor die Werte als Parameter zu
                      // übergeben?
        super(SiriusGUI.WIDTH / 2, SiriusGUI.HEIGHT - 50);
    }

    @Override
    protected String getImage() {
        String input = switch (SiriusGUI.getPlayerInput()) { // Wieso Methodenaufruf von dieser Form?
            case PlayerInput.NONE -> "player-none"; // Methode returnt den Input des Spielers als String, je nach Input
            case PlayerInput.LEFT -> "player-left";
            case PlayerInput.RIGHT -> "player-right";
        };
        return input;
    }

    @Override
    protected void update(double time, double deltaTime) { // Wie könnte man das direkt mit SiriusGUI.getPlayerInput()
                                                           // implementieren?

        if (getImage().equals("player-left") || getImage().equals("player-right")) { // Wenn Spieler nach links oder
                                                                                     // rechts will
            if (getImage().equals("player-left") && (x - 100 * deltaTime) < 0) { // Wenn Spieler nach links will und
                                                                                 // außerhalb von Bildschirm wäre
                x = SiriusGUI.WIDTH;
            } else if (getImage().equals("player-right") && (x + 100 * deltaTime) > SiriusGUI.WIDTH) { // Wenn spieler
                                                                                                       // nach rechts
                                                                                                       // will und
                                                                                                       // außerhalb von
                                                                                                       // Bildschirm
                                                                                                       // wäre
                x = 0;
            } else if (getImage().equals("player-left")) { // Wenn Spieler nach links will und innerhalb von Bildschrim
                                                           // ist
                x += -100 * deltaTime;
            } else if (getImage().equals("player-right")) { // Wenn Spieler nach rechts will und innerhalb von
                                                            // Bildschirm ist
                x += 100 * deltaTime;
            }
        }
    }

    double nextProjectile = 0;

    @Override
    public Projectile emit(double time) {
        if (time - nextProjectile >= 2.0) { //Warum nicht time % 2 == 0?
            nextProjectile = time;
            return new Projectile(getX(), getY() - 16, true);
        } else {
            return null;
        }
    }

}
