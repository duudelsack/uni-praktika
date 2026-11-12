public class MonteCarloIntegration {

    private static final int MIN_ITERATIONS = 100;
    // mindestens 100 Durchläufe
    // bei den ersten Durchläufen werden zufällig Punkte gewählt die keinen change verursachen
    private static final int MAX_ITERATIONS = 100_000;
    // maximal 100.000 Durchläufe
    private static final double MIN_CHANGE = 1e-5;
    // Beended es wenn sich das Ergebnis nicht mehr stark ändert

    public static double function(double x) {
        //return x;
        return Math.sin(Math.PI * x);
    }
    // Ziel ist die Fläche unter dieser Funktion im Intervall [0,1] zu berechnen

    private static boolean samplePoint(){
        double x = Math.random();
        double y = Math.random();

        return y <= function(x); // liegt der Punkt unter der funktion?: ja -> zählt fürs Integral
    }

    public static void main(String[] args) {

        double approxInt = 0;   //line 21
        double change;
        int allPoints = 0;
        int pointsUnderCurve = 0;
        double newApproxInt;

        do {

            if (samplePoint())
                pointsUnderCurve++;

            allPoints++;

            newApproxInt = ((double) pointsUnderCurve / allPoints);

            change = Math.abs(approxInt - newApproxInt);

            approxInt = newApproxInt;

            System.out.printf("Iteration %d: %.5f (%f)%n", allPoints, approxInt, change);

        } while ((change > MIN_CHANGE || allPoints < MIN_ITERATIONS) && allPoints < MAX_ITERATIONS);

    }
}
