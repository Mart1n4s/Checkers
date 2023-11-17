import java.util.Objects;
import java.util.Scanner;

public class Validations {
    private final char EMPTY_SPACE = '.';
    private final int ONE_SQUARE = 1;
    private final int TWO_SQUARES = 2;
    private final int PLAYER_1 = 1;
    private final int PLAYER_2 = 2;
    private final int POINTS_DIVIDER = 2;
    Game game;
    Board board;
    Player currentPlayer;
    private int startFirstChar;
    private int startSecondChar;
    private int endFirstChar;
    private int endSecondChar;

    public Validations(int startFirstChar, int startSecondChar, int endFirstChar, int endSecondChar, Player currentPlayer, Board board, Game game) {
        this.startFirstChar = startFirstChar;
        this.startSecondChar = startSecondChar;
        this.endFirstChar = endFirstChar;
        this.endSecondChar = endSecondChar;
        this.currentPlayer = currentPlayer;
        this.board = board;
        this.game = game;
    }

    public Validations() {
    }

    public int getStartFirstChar() {
        return startFirstChar;
    }

    public int getStartSecondChar() {
        return startSecondChar;
    }

    public int getEndFirstChar() {
        return endFirstChar;
    }

    public int getEndSecondChar() {
        return endSecondChar;
    }

    public void setStartFirstChar(int startFirstChar) {
        this.startFirstChar = startFirstChar;
    }

    public void setStartSecondChar(int startSecondChar) {
        this.startSecondChar = startSecondChar;
    }

    public void setEndFirstChar(int endFirstChar) {
        this.endFirstChar = endFirstChar;
    }

    public void setEndSecondChar(int endSecondChar) {
        this.endSecondChar = endSecondChar;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void validateCoordinates(){
        if (checkBoardColumn(startFirstChar) || checkBoardColumn(endFirstChar)) {
            System.out.println("Wrong coordinates. No such column");
        }
        else if (!checkPlayerPiece(board.getBoard()))  {
            System.out.println("Must move only your piece");
        }
        else if (!checkLocation(board.getBoard(), endFirstChar, endSecondChar)) {
            System.out.println("Location is not empty");
        }
        else if (!checkDiagonalMove()) {
            System.out.println("Bad move. You must move diagonally");
        }
        else {
            movePiece();
        }
    }

    public boolean checkBoardColumn(int firstChar) {
        return firstChar == 0;
    }
    public boolean checkPlayerPiece(char[][] board) {
        return currentPlayer.getPlayerPawnPiece() == board[startSecondChar][startFirstChar]
                || currentPlayer.getPlayerKingPiece() == board[startSecondChar][startFirstChar];
    }
    public boolean checkLocation(char[][] board, int firstChar, int secondChar) {
        return board[secondChar][firstChar] == EMPTY_SPACE;
    }
    public boolean checkDiagonalMove() {
        return Math.abs(endFirstChar - startFirstChar) == Math.abs(endSecondChar - startSecondChar);
    }

    public boolean checkPawnPiece(char[][] board) {
        return currentPlayer.getPlayerPawnPiece() == board[startSecondChar][startFirstChar];
    }
    public boolean isOnePositionMove() {
        return Math.abs(endFirstChar - startFirstChar) == ONE_SQUARE;
    }

    public boolean isTwoPositionsMove() {
        return Math.abs(endFirstChar - startFirstChar) == TWO_SQUARES;
    }

    public void movePiece() {
        if(isOnePositionMove()) {
            moveOnePosition();
        } else if (isTwoPositionsMove()){
            moveTwoPositions();
        } else {
            System.out.println("Cannot move like that");
        }
    }

    public boolean isForwardMove() {
        if(currentPlayer.getPlayerNumber() == PLAYER_1 && (endSecondChar - startSecondChar) < 0) {
            return false;
        } else if (currentPlayer.getPlayerNumber() == PLAYER_2 && (endSecondChar - startSecondChar) > 0) {
            return false;
        }
        return true;
    }

    public boolean canCapture() {
        char[][] checkersBoard = board.getBoard();
        int jumpedX = (startFirstChar + endFirstChar) / POINTS_DIVIDER;
        int jumpedY = (endSecondChar + startSecondChar) / POINTS_DIVIDER;
        if(currentPlayer.getPlayerPawnPiece() != checkersBoard[jumpedY][jumpedX] && currentPlayer.getPlayerKingPiece() != checkersBoard[jumpedY][jumpedX]
                && checkersBoard[jumpedY][jumpedX] != EMPTY_SPACE) {
            checkersBoard[jumpedY][jumpedX] = EMPTY_SPACE;
            return true;
        } else {
            System.out.println("Cannot go like that");
        }
        return false;
    }

    public void moveOnePosition() {
        if((checkPawnPiece(board.getBoard()) && isForwardMove()) || !checkPawnPiece(board.getBoard())) {
            board.updateBoard(this, currentPlayer);
            game.switchPlayer();
        } else {
            System.out.println("Cant go backwards with pawn");
        }
    }

    public void moveTwoPositions() {
        if((checkPawnPiece(board.getBoard()) && isForwardMove()) || !checkPawnPiece(board.getBoard())) {
            if(canCapture()) {
                board.updateBoard(this, currentPlayer);
                canCaptureMore();
            }
        } else {
            System.out.println("Cant capture backwards with pawn");
        }
    }

    public void canCaptureMore() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Can capture more? y / n");
        board.printBoard();
        String input = sc.nextLine();

        if(Objects.equals(input, "y")) {
            game.writeInput();
        } else if (Objects.equals(input, "n")) {
            game.switchPlayer();
        } else {
            canCaptureMore();
        }
    }
}