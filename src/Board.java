import java.util.ArrayList;

/**
 *
 * @author Steven
 */
public class Board {

    static int empty = 0;
    static int playerX = 1;
    static int playerO = 2;
    int[] boardArr = new int[9];
    public int agentMove;

    /**
     *
     * @param player Either the Comp. or the User
     * @return a player has gotten 3 symbols in a row
     */
    public boolean hasWon(int player) {
        return (boardArr[0] == boardArr[1] && boardArr[0] == boardArr[2] && boardArr[0] == player) || (boardArr[3] == boardArr[4] && boardArr[3] == boardArr[5] && boardArr[3] == player) //rows 1 and 2
                || (boardArr[6] == boardArr[7] && boardArr[6] == boardArr[8] && boardArr[6] == player) || (boardArr[0] == boardArr[3] && boardArr[0] == boardArr[6] && boardArr[0] == player)//row 3 and col 1
                || (boardArr[1] == boardArr[4] && boardArr[1] == boardArr[7] && boardArr[1] == player) || (boardArr[2] == boardArr[5] && boardArr[2] == boardArr[8] && boardArr[2] == player)// col 2 and 3
                || (boardArr[0] == boardArr[4] && boardArr[0] == boardArr[8] && boardArr[0] == player) || (boardArr[2] == boardArr[4] && boardArr[2] == boardArr[6] && boardArr[2] == player);// diagonals
    }

    /**
     *
     * @return if either player has won the game or if it is a draw.
     */
    public boolean GameOver() {
        return hasWon(playerX) || hasWon(playerO) || getAvailableSpaces().isEmpty();
    }

    /**
     * Prints out the current state of the tic-tac-toe board
     */
    public void drawBoard() {
        for (int i = 0; i < 9; i++) {
            String val = "|-";
            if (i % 3 == 0 && i > 1) {
                System.out.print("|\n");
            }
            if (boardArr[i] == playerX) {
                val = "|X";
            } else if (boardArr[i] == playerO) {
                val = "|O";
            }
            System.out.print(val + "");
        }
        System.out.print("|");
        System.out.println("\n");
    }

    /**
     *
     * @return list of spots on the board where no symbols are currently
     */
    public ArrayList<Integer> getAvailableSpaces() {
        ArrayList<Integer> availableSpaces = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (boardArr[i] == empty) {
                availableSpaces.add(i);
            }
        }
        return availableSpaces;
    }

    /**
     *
     * @param pos The position being checked
     * @param player Sets pos to a value based on what player selects it. Used
     * to update the board.
     * @return
     */
    public boolean isNotAvailable(int pos, int player) {
        if (boardArr[pos] != empty) {
            return false;
        }
        boardArr[pos] = player;
        return true;
    }

    /**
     *
     * @param depth Depth of exploration of the spots of the board
     * @param player Player for which the state is evaluated
     * @return
     */
    public double miniMax(int depth, int player) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        if (hasWon(playerX)) {
            return 1;
        }
        if (hasWon(playerO)) {
            return -1;
        }

        ArrayList<Integer> availableSpaces = getAvailableSpaces();
        if (availableSpaces.isEmpty()) {// game is over if there are no avialble spaces
            return 0;
        }

        for (int i = 0; i < availableSpaces.size(); i++) {
            int pos = availableSpaces.get(i);

            if (player == playerX) {
                isNotAvailable(pos, playerX);//sets the current position at i to not available before recursively calling to check other positions
                double score = miniMax(depth + 1, playerO);//increases the depth for the other player
                if (max < score) { //sets max to the grater value between score and the current max
                    max = score;
                }

                if (score >= 0 && depth == 0) {
                    agentMove = pos;
                }
                

            } else if (player == playerO) {
                isNotAvailable(pos, playerO);//sets the current position at i to not available before recursively calling to check other positions
                double score = miniMax(depth + 1, playerX);//increases the depth for the other player
                if (min > score) {//sets min to the lesser value between score and the current min
                    min = score;
                }

            }
            boardArr[pos] = empty;
        }
        if (player == playerX) {//if computer's turn, return max, else return min
            return max;
        }
        return min;
    }

}
