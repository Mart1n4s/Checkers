public class Board {
    private final char[][] board;
    private final int BOARD_ROWS = 9;
    private final int BOARD_COLS = 9;
    private final int BOARD_FIRST_ROW = 1;
    private final int BOARD_LAST_ROW = 8;
    private final char EMPTY_SPACE = '.';
    private final int PLAYER_1 = 1;
    private final int PLAYER_2 = 2;

    public Board(Player player1, Player player2) {
        board = new char[BOARD_ROWS][BOARD_COLS];
        initializeBoard(player1, player2);
    }

    public void initializeBoard(Player player1, Player player2){
        board[0][1] = 'A';
        board[0][2] = 'B';
        board[0][3] = 'C';
        board[0][4] = 'D';
        board[0][5] = 'E';
        board[0][6] = 'F';
        board[0][7] = 'G';
        board[0][8] = 'H';
        board[1][0] = '1';
        board[2][0] = '2';
        board[3][0] = '3';
        board[4][0] = '4';
        board[5][0] = '5';
        board[6][0] = '6';
        board[7][0] = '7';
        board[8][0] = '8';

        fillBoardEmptySpace();
        fillPlayer1BoardSide(player1);
        fillPlayer2BoardSide(player2);
    }

    public void fillBoardEmptySpace() {
        for (int i = 1; i < BOARD_ROWS; i++) {
            for (int j = 1; j < BOARD_COLS; j++) {
                board[i][j] = EMPTY_SPACE;
            }
        }
    }

    public void fillPlayer1BoardSide(Player player1) {
        for(int i = 1; i < 4; i++){
            if(i % 2 == 0) {
                for(int j = 1; j < board[i].length; j += 2){
                    board[i][j] = player1.getPlayerPawnPiece();
                }
            } else {
                for (int j = 2; j < board[i].length; j += 2) {
                    board[i][j] = player1.getPlayerPawnPiece();
                }
            }
        }
    }

    public void fillPlayer2BoardSide(Player player2) {
        for(int i = 8; i > 5; i--){
            if(i % 2 == 0) {
                for(int j = 7; j >= 0; j -= 2){
                    board[i][j] = player2.getPlayerPawnPiece();
                }
            } else {
                for (int j = 8; j > 0; j -= 2) {
                    board[i][j] = player2.getPlayerPawnPiece();
                }
            }
        }
    }

    public void updateBoard(Validations validations, Player currentPlayer) {
        board[validations.getEndSecondChar()][validations.getEndFirstChar()] = currentPlayer.getPlayerPawnPiece();
        if(currentPlayer.getPlayerPawnPiece() == board[validations.getStartSecondChar()][validations.getStartFirstChar()]) {
            transformToKing(validations, currentPlayer);
        }
        board[validations.getStartSecondChar()][validations.getStartFirstChar()] = EMPTY_SPACE;
    }

    public void transformToKing(Validations validations, Player currentPlayer) {
        if((validations.getEndSecondChar() == BOARD_LAST_ROW && currentPlayer.getPlayerNumber() == PLAYER_1)
                || (validations.getEndSecondChar() == BOARD_FIRST_ROW && currentPlayer.getPlayerNumber() == PLAYER_2)) {
            board[validations.getEndSecondChar()][validations.getEndFirstChar()] = currentPlayer.getPlayerKingPiece();
        }
    }
    
    public void printBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char[][] getBoard() {
        return board;
    }
}