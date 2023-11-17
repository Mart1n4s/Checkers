public class Player {
    private int playerNumber;
    private char playerKingPiece;
    private char playerPawnPiece;

    public Player(int playerNumber, char playerKingPiece, char playerPawnPiece) {
        this.playerNumber = playerNumber;
        this.playerKingPiece = playerKingPiece;
        this.playerPawnPiece = playerPawnPiece;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public char getPlayerKingPiece() {
        return playerKingPiece;
    }

    public char getPlayerPawnPiece() {
        return playerPawnPiece;
    }
}
