package sirius;

import java.util.concurrent.ThreadLocalRandom;

public class SiriusGameManager {

  private SiriusGUI siriusGUI;
  private Player player;

  public SiriusGameManager() {
    // DONT'T CHANGE
    this.siriusGUI = new SiriusGUI(this);
    // /DONT'T CHANGE
    this.player = new Player(); // Initialisierung einer neuen Player-Instanz
    addActor(player);
  }

  public void update(double time, double deltaTime) {
    for (int i = 0; i < actors.length; i++) {
      if (actors[i] != null) {
        actors[i].update(time, deltaTime);
        siriusGUI.draw(actors[i].getImage(), actors[i].getX(), actors[i].getY()); // Wieso siriusGUI.draw() und nicht
                                                                                  // actors[i].draw()?
      }
    }
    if (spawnOpponent()) { // Wenn spawnOpponent == true, dann wird Gegner an zufälliger x-Koordinate
                           // gespawnt.
      double xRandom = ThreadLocalRandom.current().nextDouble(0, SiriusGUI.WIDTH);
      double random = Math.random();
      if (random < 0.3) {
        Actor OpponentSimple = new OpponentSimple(xRandom);
        addActor(OpponentSimple);
      } else if (random >= 0.3 && random < 0.6){
        Actor OpponentChaser = new OpponentChaser(player, xRandom);
        addActor(OpponentChaser);
      } else {
        Actor OpponentLurker = new OpponentLurker(xRandom);
        addActor(OpponentLurker);
      }

    }
    for (int i = 0; i < actors.length; i++) { // Schleife prüft, ob Actors außerhalb von Bildschirm, falls ja, wird
                                              // Actor entfernt
      if (actors[i] != null) {
        if (actors[i].getY() < 0 || actors[i].getY() > SiriusGUI.HEIGHT) {
          actors[i] = null;
        }
      }
    }
    detectCollision();
    for (int i = 0; i < actors.length; i++) { // Nach Implementierung funktionert detectCollision() nicht mehr richtig.
      if (actors[i] instanceof ProjectileEmitter) {
        ProjectileEmitter emitter = (ProjectileEmitter) actors[i];
        Actor projectile = emitter.emit(time);
        if (projectile != null) {
          addActor(projectile);
        }
      }
    }
  }

  /*public void detectCollision() {
    for (int i = 0; i < actors.length; i++) {
      if (actors[i] != null) {
        for (int j = 0; j < actors.length; j++) {
          if (actors[j] != null && actors[j] != actors[i]) {
            double xDiff = actors[i].getX() - actors[j].getX(); // Differenz x-Koordinaten
            double yDiff = actors[i].getY() - actors[j].getY(); // Differenz y-Koordinaten
            if (Math.sqrt((xDiff * xDiff) + (yDiff * yDiff)) <= 8) { // Wenn Distanz <= 8, dann Actors entfernen
              actors[i] = null;
              actors[j] = null;
            }
          }
        }
      }
    }
  }*/

  public void detectCollision() {
    for (int i = 0; i < actors.length; i++) {
        for (int j = 0; j < actors.length; j++) {
            if (i != j && actors[i] != null && actors[j] != null) {
                double xDiff = actors[i].getX() - actors[j].getX();
                double yDiff = actors[i].getY() - actors[j].getY();
                if (Math.sqrt(xDiff * xDiff + yDiff * yDiff) <= 8) {
                    actors[i] = null;
                    actors[j] = null;
                }
            }
        }
    }
}


  // #actor
  private double nextSpawnTime = 0d;
  private Actor[] actors = new Actor[1024];

  private boolean spawnOpponent() {
    double time = siriusGUI.getTime();
    if (time > nextSpawnTime) {
      nextSpawnTime = time + Math.max(0.5d, 8d - 8 * Math.pow(time / 120, 0.9));
      return true;
    } else
      return false;
  }

  private void addActor(Actor actor) {
    if (actor == null)
      throw new IllegalArgumentException("actor must not be null");

    for (int i = 0; i < actors.length; i++) {
      if (actors[i] == null) {
        actors[i] = actor;
        break;
      }
    }
  }
}
