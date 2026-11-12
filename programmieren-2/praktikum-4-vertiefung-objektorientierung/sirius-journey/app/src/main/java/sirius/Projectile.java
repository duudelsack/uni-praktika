package sirius;

public class Projectile extends Actor{

    boolean fromPlayer;

    protected String getImage(){ //Je nach Ursprung besitzt Projectile andere Grafik
        if (fromPlayer){
            return "projectile-player";
        } else {
            return "projectile-opponent";
        }
    }

    public Projectile(double x, double y, boolean fromPlayer){ //Konstruktor, berücksichtigt Ursprung von Projectile
        super(x, y);
        this.fromPlayer = fromPlayer;
    }

    protected void update(double time, double deltaTime){ //Code, um Projectile zu bewegen
        if (fromPlayer){
            y -= 200*deltaTime;
        } else {
            y += 200*deltaTime;
        }
    }
}
