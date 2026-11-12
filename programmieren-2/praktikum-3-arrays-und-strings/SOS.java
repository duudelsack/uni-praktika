import java.util.Scanner;

public class SOS {

    public static Entry[][] getExample() {
        char[][] chars = {
            { ' ', ' ', ' ', ' ', ' ', 'S' },
            { ' ', ' ', 'S', ' ', ' ', ' ' },
            { ' ', 'O', 'S', 'Ø', ' ', ' ' },
            { ' ', 'S', ' ', '$', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'S', ' ', 'O', ' ' }
        };

        Entry[][] board = new Entry[6][6];

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                char c = chars[i][j];
                board[i][j] = (c == ' ') ? null : Entry.fromDisplay(c);
            }
        }

        return board;
    }

    public static void checkBoard(Entry[][] board) {
        if (board == null || board.length < 3) {
            throw new IllegalArgumentException("Board zu klein!");
        }
        for (Entry[] row : board) {
            if (row == null || row.length != board.length) {
                throw new IllegalArgumentException("Board nicht quadratisch!");
            }
        }
    }

    public static void printBoard(Entry[][] board) {
        checkBoard(board);
        int n = board.length;

        System.out.print("  ");
        for (int i = 0; i < n; i++) System.out.print(i + " ");
        System.out.println();

        System.out.print("  ");
        for (int i = 0; i < n; i++) System.out.print("+-");
        System.out.println("+");

        for (int i = 0; i < n; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < n; j++) {
                char c = (board[i][j] == null) ? ' ' : board[i][j].getDisplay();
                System.out.print("|" + c);
            }
            System.out.println("|");

            System.out.print("  ");
            for (int j = 0; j < n; j++) System.out.print("+-");
            System.out.println("+");
        }
    }

    public static boolean boardFull(Entry[][] board) {
        checkBoard(board);
        for (Entry[] row : board) {
            for (Entry cell : row) {
                if (cell == null) return false;
            }
        }
        return true;
    }

        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Entry[][] board = new Entry[3][3];

        while (!boardFull(board)) {
            printBoard(board);

            System.out.print("Enter row: ");
            int row = scanner.nextInt();
            System.out.print("Enter col: ");
            int col = scanner.nextInt();
            System.out.print("Enter entry (S or O): ");
            char c = scanner.next().toUpperCase().charAt(0);

            if (c != 'S' && c != 'O') {
                System.out.println("Ungültiges Zeichen!");
                continue;
            }
            if (row < 0 || row >= board.length || col < 0 || col >= board.length) {
                System.out.println("Ungültige Position!");
                continue;
            }
            if (board[row][col] != null) {
                System.out.println("Feld bereits belegt!");
                continue;
            }

            board[row][col] = Entry.fromDisplay(c);
        }

        printBoard(board);
        System.out.println("Fertig! Kein freies Feld mehr.");
    }
}

