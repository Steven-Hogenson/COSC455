import java.util.Scanner;

/**
 *
 * @author Steven
 */
public class Main {

    public static void main(String args[]) {
       Board b = new Board();
        Scanner sc = new Scanner(System.in);
        b.drawBoard();
        System.out.println("Enter which player to go first: (1) AI or (2) User");
        String usIn = sc.next();
        if (Integer.parseInt(usIn) == b.playerX) {
            int randPos = (int) (Math.random() * 9);//if the comp. goes first, place first X in a random spot on the board
            //int randPos = 3;
            b.isNotAvailable(randPos, b.playerX);
            b.drawBoard();
        }
        while (!b.GameOver()) {

            boolean emptySpace = true;

            do {
                if (!emptySpace) {
                    System.out.println("Space is already filled.");
                }

               System.out.print("Enter a position (0-8) or type '?' to display other commands: ");
                usIn = sc.next();
                switch (usIn) {//commands
                    case "?":
                        printCommands();
                        break;
                    case "exit":
                        System.exit(0);
                    case "show":
                        b.drawBoard();
                        break;
                    default:
                        emptySpace = b.isNotAvailable(Integer.parseInt(usIn), b.playerO);//sets empty space to false if a space is currently occupied
                        break;
                }
            } while (!emptySpace);
            if (usIn.equals("0") || usIn.equals("1") || usIn.equals("2") || usIn.equals("3")
                    || usIn.equals("4") || usIn.equals("5") || usIn.equals("6") || usIn.equals("7")
                    || usIn.equals("8")) {
                b.drawBoard();

             
            b.miniMax(0, b.playerX);
            b.isNotAvailable(b.agentMove, b.playerX);
            b.drawBoard();
            System.out.println("AI chose position: " + b.agentMove);
                
            }
        }

         //Check if either player has won
        if (b.hasWon(b.playerX)) {
            System.out.println("You lost.");
        } else if (b.hasWon(b.playerO)) {
            System.out.println("You win.");
        } else {
            System.out.println("Draw.");
        }
    }
    
    /**
     * Print out statements to let the user know of available commands
     */
    static void printCommands() {
        System.out.println("You can type a number 0-8 to enter an 'O' into the designated spot on the board; where 0 is top-left, and 8 is bottom-right.");
        System.out.println("You can type 'show' to print the tic-tac-toe board.");
        System.out.println("You can type 'exit' to quit the game.");

    }

}
