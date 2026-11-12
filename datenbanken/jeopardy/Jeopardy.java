import java.sql.*;
import java.util.Scanner;

public class Jeopardy
{
    private Connection conn;
    private Scanner sc;
    private Integer currentGame;
    private Integer currentGamePlayfield;
    private String[][] answeredQuestions = new String[5][6];
    private Integer[][] questions = new Integer[5][6];
    private String[] categories = new String[6];
    
    // Die SQL Befehle, die Sie sinnvoll ergaenzen
    private String sql_pragma = "PRAGMA foreign_keys=TRUE";
    private String sql_new_game = "INSERT INTO game (id, name, playfield) VALUES (?, ?, ?)";
    private String sql_playfield_from_game = "SELECT playfield, name FROM game WHERE id=?"; // Vorgabe
    private String sql_one_playfield = "SELECT * FROM playfield WHERE id = ?";
    private String sql_turns_by_game = "SELECT * FROM turn WHERE game_id = ? ORDER BY timestamp";
    private String sql_get_question = "SELECT question, answer FROM question WHERE id = ?";
    private String sql_insert_turn = "INSERT INTO turn (game_id, team, question) VALUES (?, ?, ?)";
    
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String BLUE = "\u001B[34m";
    public static String YELLOW = "\u001B[33m";
    public static String NORMAL = "\u001B[0m";
    

    /**
     * Der Standardkonstruktor, der die Datenbank jeopardy.db im aktuellen Verzeichnis sucht.
     * Ist bereits vollstaendig implementiert und muss nicht geaendert werden.
     */
    public Jeopardy()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:jeopardy.db");
            PreparedStatement ps = conn.prepareStatement(sql_pragma);
            ps.execute();
            sc = new Scanner(System.in);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Getter
     * @return Die aktuelle Spiel ID
     */
    public Integer getCurrentGame() {
        return currentGame;
    }
    
    /**
     * Das Hauptmenue - bereits vollstaendig implementiert, muss nicht geaendert werden.
     * @return
     */
    public String getMainMenu() {
        String s = ".q\t Quit\n";
        s += ".ng\t New game\n";
        s += ".lg\t Load game\n";
        s += ".pg\t Print game\n";
        s += ".pl\t Draw a question\n";
        s += ".s\t Set team in turn\n";
        return s;
    }
    
    /**
     * Holt die Spielfeld ID aus dem Spiel - bereits vollstaendig implementiert.
     * Kann als Beispiel betrachtet werden.
     * @param gameId Die Spiel ID zum Spielfeld
     * @return null, falls Fehler
     */
    private Integer getPlayfieldFromGame(int gameId) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql_playfield_from_game);
            ps.setInt(1, gameId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("playfield");
            } else return null;
             
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private boolean getQuestionsFromPlayfield(int playfield) {
        try {
            // 1. Gewünschtes Spielfeld laden
            // 2. Kategorien in this.categories schreiben
            // 3. Fragen in this.questions schreiben
            PreparedStatement ps = conn.prepareStatement(sql_one_playfield);
            ps.setInt(1, playfield);
            
            // ... hier Ihr Code
            ResultSet rs = ps.executeQuery();
            if (!rs.next()){                    //Ergebnisse aus DB holen, falls leer -> False
                return false;
            }

            for (int i = 0; i < 6; i++){
                String column_name = "cat" + (i + 1); //Kategorien in Liste hinzufügen
                categories[i] = rs.getString(column_name);
            }

            for (int cat = 1; cat <= 6; cat++){
                for (int pos = 1; pos <= 5; pos++) {
                    String qcol = "q" + cat + pos;          //Array mit Fragen befüllen
                    int qid = rs.getInt(qcol);
                    questions[pos -1][cat - 1] = qid;
                }
            }
            return true;
            
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * Spiel laden und Objektavriablen initialisieren.
     * Methode ist bereits vollstaendig implementiert und muss nicht geaendert werden.
     */
    public void loadGame() {
        sc = new Scanner(System.in);
        try {             
            System.out.print("Enter ID of the game to load: ");
            this.currentGame = sc.nextInt();
            this.currentGamePlayfield = getPlayfieldFromGame(this.currentGame);
            if (!getQuestionsFromPlayfield(this.currentGamePlayfield)) {
                System.out.println("Something went wrong getting the questions from playfield.");
            }
        }
        catch(Exception e) {
            if (this.currentGame == null) System.out.println("No such game.");
            if (this.currentGamePlayfield == null) System.out.println("No playfield found.");
        }
    }
    
    private boolean getAllTurnsForGame(int gameId) {
        try {
            PreparedStatement ps;
            String[][] answered = new String[5][6];
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 6; x++) {
                    ps = conn.prepareStatement(sql_turns_by_game);
                    // 1. Alle turns mit gameId laden
                    // 2. Feld answered füllen und die Teams eintragen
                    //    Wenn kein Eintrag, dann ignorieren!

                    // ... hier Ihr Code
                    int qid = questions[y][x];
                    ps.setInt(1, gameId);
                    ResultSet rs = ps.executeQuery();
                    String lastTeam = null;

                    while (rs.next()){      //Durch result iterieren, falls vergleich passt, speicher das Team
                        int turnQ = rs.getInt("question");
                        if (turnQ == qid){
                            lastTeam = rs.getString("team");
                        }
                    }

                    if (lastTeam != null){
                        answered[y][x] = lastTeam; //Team in Array eintragen
                    }
                }
            }
            answeredQuestions = answered; // To remove old answers
            return true;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * Spielfeld eines Spiels am Bildschirm ausgeben.
     * Diese Methode ist bereits implementiert und muss nicht geaendert werden (darf aber natuerlich).
     * @param gameId Die ID des Spiels, das angezeigt werden soll
     */
    public void printGame(int gameId) {
        try {
            getAllTurnsForGame(gameId);
            // Print categories
            for (int i = 0; i < 6; i++) {
                System.out.printf("| %20s ", this.categories[i]);
            }
            System.out.println();
            // Print questions
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 6; x++) {
                    String aq = answeredQuestions[y][x];
                    if (aq == null) {
                        System.out.printf("| %20d ", (y+1)*100);
                    } else {
                        String color = NORMAL;
                        if (aq.equals("red")) color = RED;
                        if (aq.equals("green")) color = GREEN;
                        if (aq.equals("yellow")) color = YELLOW;
                        if (aq.equals("none")) color = YELLOW;
                        if (aq.equals("blue")) color = BLUE;
                        System.out.printf("| %20s ", color + aq + NORMAL);
                        System.out.print("         ");
                    }
                }
                System.out.println("|");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void drawQuestion() {
        try {
            sc = new Scanner(System.in);
            System.out.print("Category: ");
            int cat = sc.nextInt();
            System.out.print("Position: ");
            int pos = sc.nextInt();
            
            PreparedStatement ps = conn.prepareStatement(sql_get_question);

            // 1. Frage laden
            // 2. Frage am Bildschirm anzeigen
            // 3. Fragen, ob Antwort angezeigt werden soll
            //    Wenn ja, Antwort anzeigen

            // ... hier Ihr Code
            int x = cat - 1;
            int y = pos - 1;        //Indizes anpassen
            int qid = questions[y][x]; //qid aus Array holen

            ps.setInt(1, qid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                String questionText = rs.getString("question");
                String answerText = rs.getString("answer");

                System.out.println(questionText);
                sc = new Scanner(System.in); //Frage ausgeben und fragen, ob Antwort gezeigt werden soll
                System.out.println("Soll die Antwort gezeigt werden? (j/n)");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("j")){
                    System.out.println(answerText);
                }

            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void setQuestion(int gameId) {
        sc = new Scanner(System.in);    
        try {
            System.out.print("Category: ");
            int cat = sc.nextInt();
            System.out.print("Position: ");
            int pos = sc.nextInt();
            sc = new Scanner(System.in);
            System.out.print("Team: ");
            String team = sc.nextLine();
            
            PreparedStatement ps = conn.prepareStatement(sql_insert_turn);

            // 1. Einen neuen Spielzug in die Datenbank einfügen -> Team wird zugewiesen
            //    Der Timestamp wird automatisch inkrementiert -> Im SQL Befehl den Timestamp entsprechend behandeln

            // ... hier Ihr Code
            int x = cat - 1;
            int y = pos - 1; //Indizes anpassen und qid abfragen
            int qid = questions[y][x];

            ps.setInt(1, gameId);
            ps.setString(2, team.toLowerCase());
            ps.setInt(3, qid);

            ps.executeUpdate(); //Werte einfügen

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }  
    }
     
    
    public int newGame() {
        try {
            sc = new Scanner(System.in);
            System.out.print("Please enter game id: ");
            int id = sc.nextInt();
            this.currentGame = id;
            System.out.print("Please enter used playfield: ");
            int pf = sc.nextInt();
            
            PreparedStatement ps = conn.prepareStatement(sql_new_game);

            // 1. Neues Spiel in die Datenbank eintragen

            // ... hier Ihr Code
            this.currentGame = id;
            this.currentGamePlayfield = pf; //Variablen setzen

            if (!getQuestionsFromPlayfield(pf)) {
                System.out.println("Error loading questions for playfield " + pf);
                return -1;
            }

            ps.setInt(1, id);
            ps.setString(2, "Game " + id); //Update "befüllen"
            ps.setInt(3, pf);

            ps.executeUpdate(); //Änderung vornehmen

            return 1; // Sinnvoll ergaenzen
        }
        catch(Exception e) {
            System.out.println("Sorry: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Alles schliessen
     */
    public void close() {
        try {
            conn.close();
            sc.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Jeopardy j = new Jeopardy();
        Scanner sc = null;
        String command = "ok";
        while(!command.equals(".q")) {
            System.out.println(j.getMainMenu());
            sc = new Scanner(System.in);
            command = sc.next();
            switch(command) {
                case ".ng" :
                    j.newGame();
                    break;
                case ".lg" :
                    j.loadGame();
                    break;
                case ".pg" :
                    j.printGame(j.getCurrentGame());
                    break;
                case ".pl" :
                    j.drawQuestion();
                    break;
                case ".s" :
                    j.setQuestion(j.getCurrentGame());
            }
        }
        sc.close();
        j.close();
    }
    
    
}
