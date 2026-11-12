import javax.swing.*;
import java.awt.*;

public class Spiralen extends JPanel {

  /**
   * @param graphics
   * @param args
   */
  private void drawSpiral(Graphics graphics, String[] args){

    int iterations = 0;
    double angle = 0;
    double shrink = 0;

    // Anzahl der eingegebenen Argumente überprüfen (ist eigentlich nciht die Aufgabe der Funktion sondern von main bzw.
    // vom Aufrufer der Funktion)
    if(args.length < 3 || args.length > 3){
      System.out.println("Es werden nur genau 3 Argumente akzeptiert!");
      return;
    }

    // Testen ob alle Argumente gecastet werden können => Wenn nicht Abbruch
    try {
      iterations = Integer.parseInt(args[0]);
      angle = Double.parseDouble(args[1]);
      shrink = Double.parseDouble(args[2]);
    } 
    catch (NumberFormatException e) {
      System.err.println("Fehler: Alle drei Parameter müssen Zahlen sein!");
      return;
    }
    
    // Es müsseb mehr wie 0 oder 0 iterationen geben
    if (iterations <= 0) {
      System.err.println("Iterations muss positiv sein.");
      return;
    }

    // shrink kann nicht kleiner wie 0 oder größer wie 1 sein. Ansonsten bekommt man keine Spirale die inwärts verläuft
    if (shrink <= 0 || shrink >= 1) {
        System.err.println("shrink muss zwischen 0 und 1 liegen.");
        return;
    }

    // Startpunkt
    //Vektor
    int x = 20;
    int y = 20;
    int dx = 400;
    int dy = 0;

    // Zeichnen der Spirale anhand der gegebenen Werte
    for (int i = 0; i < iterations; i++)
    {
      graphics.drawLine(x, y, x+dx, y+dy);

      x = x + dx;
      y = y + dy;

      // die Methode to Radians wird benötigt um den double angle in Radians umzurechnen
      // was der gängigen Einheit für das berechnen von sin und cos werten entpricht (Winkel)
      double newDx = (dx * Math.cos(Math.toRadians(angle)) - dy * Math.sin(Math.toRadians(angle))) * shrink;
      double newDy = (dx * Math.sin(Math.toRadians(angle)) - dy * Math.cos(Math.toRadians(angle))) * shrink;

      dx = (int)newDx;
      dy = (int)newDy;
    }



  //graphics.drawLine(0,0,WIDTH,HEIGHT);
  }

  // DONT'T CHANGE ANYTHING BELOW HERE

  private String[] args;

  private final int WIDTH = 600;
  private final int HEIGHT = 600;

  private Spiralen(String[] args){
    super();
    this.args = args;
    JFrame frame = new JFrame("Spiralen");
    frame.setSize(WIDTH, HEIGHT);
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    frame.add(this);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setVisible(true);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setColor(java.awt.Color.WHITE);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    g.setColor(new java.awt.Color(0.6f, 0, 0));
    drawSpiral(g, args);
  }

  public static void main(String[] args) {
    Spiralen spiralen = new Spiralen(args);
    
  }
    
}
