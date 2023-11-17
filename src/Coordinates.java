import static java.lang.Character.getNumericValue;

public class Coordinates {

    private final int COORDINATES_LENGTH = 2;
    Player currentPlayer;
    Board board;
    Game game;
    private String coordinates;

    public Coordinates(String coordinates, Player currentPlayer, Board board, Game game) {
        this.coordinates = coordinates;
        this.currentPlayer = currentPlayer;
        this.board = board;
        this.game = game;
    }

    public Coordinates() {
    }

    public boolean checkCoordinate(String coordinates){
        char firstChar = coordinates.charAt(0);
        char secondChar = coordinates.charAt(1);
        return Character.isLetter(firstChar) && Character.isDigit(secondChar) && coordinates.length() == COORDINATES_LENGTH;
    }

    public void splitInput(){
        String[] splittedInput = coordinates.trim().split(" ");
        String startCoordinate = splittedInput[0];
        String endCoordinate = splittedInput[1];

        if (splittedInput.length != COORDINATES_LENGTH || !checkCoordinate(startCoordinate) || !checkCoordinate(endCoordinate)) {
            System.out.println("Wrong input");
        } else {

            int startFirstChar = convertFirstCoordinate(startCoordinate);
            int startSecondChar = convertSecondCoordinate(startCoordinate);
            int endFirstChar = convertFirstCoordinate(endCoordinate);
            int endSecondChar = convertSecondCoordinate(endCoordinate);

            Validations validations = new Validations(startFirstChar, startSecondChar, endFirstChar, endSecondChar, currentPlayer, board, game);
            validations.validateCoordinates();
        }
    }

    public int convertSecondCoordinate(String coordinate){
        return getNumericValue(coordinate.charAt(1));
    }

    public int convertFirstCoordinate(String coordinate){
        return switch (coordinate.charAt(0)) {
            case 'A', 'a' -> 1;
            case 'B', 'b' -> 2;
            case 'C', 'c' -> 3;
            case 'D', 'd' -> 4;
            case 'E', 'e' -> 5;
            case 'F', 'f' -> 6;
            case 'G', 'g' -> 7;
            case 'H', 'h' -> 8;
            default -> 0;
        };
    }

}
