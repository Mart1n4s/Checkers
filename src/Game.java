import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {

    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;

    public Game(){

        player1 = new Player(1, '⬤', 'o');
        player2 = new Player(2, '╳', 'x');
        currentPlayer = player1;
        board = new Board(player1, player2);
    }

    public void writeInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose where to go? Example: B3 C4 or b3 c4");
        System.out.println("Surrender: s");
        String input = sc.nextLine();

        if(input.equals("s")) {
            announceWinner();
            exitGame();
        }


        Coordinates coordinates = new Coordinates(input, currentPlayer, board, this);
        coordinates.splitInput();
    }

    public void switchPlayer(){
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public void play(){
        while(true) {
            if (checkGameOver(currentPlayer.getPlayerPawnPiece(), currentPlayer.getPlayerKingPiece())) {
                announceWinner();
                exitGame();
            }
            if(currentPlayer.getPlayerNumber() == player1.getPlayerNumber()) System.out.println("Player 1 turn...");
            else System.out.println("Player 2 turn...");
            board.printBoard();
            writeInput();
        }
    }

    public boolean checkGameOver(char Pawn, char King){
        Set<Character> removedBoardPieces = new HashSet<>();

        for (char[] row : board.getBoard()) {
            for (char c : row) {
                removedBoardPieces.add(c);
            }
        }
        return !removedBoardPieces.contains(King) && !removedBoardPieces.contains(Pawn);
    }

    public void announceWinner() {
        if(currentPlayer.getPlayerNumber() == player1.getPlayerNumber()) {
            System.out.println("Player 2 wins");
        } else {
            System.out.println("Player 1 wins");
        }
    }

    public void exitGame() {
        System.exit(0);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
