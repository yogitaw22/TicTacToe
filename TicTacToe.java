import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        char[][] grid = createGrid(3, 3);
        System.out.println("\n***** WELCOME *****");
        startGame(grid);
    }

    public static char[][] createGrid(int rows, int columns) {
        char[][] grid = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = ' ';
            }
        }
        return grid;
    }

    public static void startGame(char[][] grid) {
        boolean winner = false;
        while (!winner) {
            // Player X's Turn
            boolean playerXSuccess = player(grid, 'X');
            checkWinner(grid);
            if (!playerXSuccess) {
                continue;
            }

            // Player O's Turn
            boolean playerOSuccess = player(grid, 'O');
            checkWinner(grid);
            if (!playerOSuccess) {
                continue;
            }
        }
    }

    public static boolean player(char[][] grid, char player) {
        displayGrid(grid);
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter your response player " + player + " (e.g., A1): ");
        String response = sc.next().toUpperCase();
        
        boolean check = checkResponse(grid, response, player);
        if (!check) {
            System.out.println("\nPlayer " + player + " should get a chance\n");
            return false;
        }
        return true;
    }

    public static boolean checkResponse(char[][] grid, String response, char player) {
        if (response.length() < 2) return false;
        
        // Convert A1, B2 etc. to array indices
        int i = response.charAt(0) - 65; // 'A' becomes 0
        int j = response.charAt(1) - 49; // '1' becomes 0

        if (i < 0 || i > 2 || j < 0 || j > 2) {
            System.out.println("\nInvalid Response\n");
            return false;
        }

        if (grid[i][j] == ' ') {
            grid[i][j] = player;
            return true;
        } else {
            System.out.println("\nResponse already submitted\n");
            return false;
        }
    }

    public static void checkWinner(char[][] grid) {
        int cntX = 0;
        int cntO = 0;

        // Horizontal winner check
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (grid[i][j] == 'X') cntX++;
                else if (grid[i][j] == 'O') cntO++;
            }
            announceWinner(grid, cntX, cntO);
            cntX = 0; cntO = 0;
        }

        // Vertical winner check
        for (int j = 0; j <= 2; j++) {
            for (int i = 0; i <= 2; i++) {
                if (grid[i][j] == 'X') cntX++;
                else if (grid[i][j] == 'O') cntO++;
            }
            announceWinner(grid, cntX, cntO);
            cntX = 0; cntO = 0;
        }

        // Diagonal conditions
        if ((grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X') ||
            (grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X')) {
            displayGrid(grid);
            System.out.println("\nX is winner");
            System.exit(0);
        }
        if ((grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O') ||
            (grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O')) {
            displayGrid(grid);
            System.out.println("\nO is winner");
            System.exit(0);
        }

        // Draw check
        boolean gridsFull = true;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (grid[i][j] == ' ') gridsFull = false;
            }
        }
        if (gridsFull) {
            displayGrid(grid);
            System.out.println("\n ITS A DRAW \n");
            System.exit(0);
        }
    }

    private static void announceWinner(char[][] grid, int cntX, int cntO) {
        if (cntX == 3) {
            displayGrid(grid);
            System.out.println("\nX is winner");
            System.exit(0);
            
        } else if (cntO == 3) {
            displayGrid(grid);
            System.out.println("\nO is winner");
            System.exit(0);
        }
    }
    

    public static void displayGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print("[" + grid[i][j] + "]");
            }
            System.out.println();
        }
    }
}