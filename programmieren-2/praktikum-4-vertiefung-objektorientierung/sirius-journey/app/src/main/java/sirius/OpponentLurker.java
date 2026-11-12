package sirius;

import java.util.concurrent.ThreadLocalRandom;

public class OpponentLurker extends Opponent implements ProjectileEmitter {

    public OpponentLurker(double x) {
        super(x);
    }

    public String getImage() {
        return "opponent-lurker";
    }

    private boolean goRight = true;

    public void update(double time, double deltaTime) {
        double yRandom = ThreadLocalRandom.current().nextDouble(0, SiriusGUI.HEIGHT / 2);
        if (this.getY() < yRandom) {
            this.y += 80 * deltaTime;
            if (this.y > yRandom) {
                this.y = yRandom;
            }
        }
        if (goRight) {
            this.x += 80 * deltaTime;
            if (this.x >= SiriusGUI.WIDTH - 50) {
                this.x = SiriusGUI.WIDTH - 50;
                goRight = false;
            }
        } else {
            this.x -= 80 * deltaTime;
            if (this.x <= 50) {
                this.x = 50;
                goRight = true;
            }
        }
    }

    double nextProjectile = 0;

    public Actor emit(double time) { // Alle 2 Sekunden werden Projectiles erstellt
        if (time - nextProjectile >= 2.0) { //Wieso dieser Vergleich und nicht % 2?
            nextProjectile = time;
            return new Projectile(getX(), getY() + 16, false);
        } else {
            return null;
        }
    }

}
