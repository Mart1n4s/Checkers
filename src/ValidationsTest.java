import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsTest {

    @Test
    public void testValidDiagonalMove() {
        Validations validations = new Validations();
        validations.setStartFirstChar(2);
        validations.setStartSecondChar(3);
        validations.setEndFirstChar(3);
        validations.setEndSecondChar(4);

        assertTrue(validations.checkDiagonalMove());
    }

    @Test
    public void testInvalidDiagonalMove() {
        Validations validations = new Validations();
        validations.setStartFirstChar(2);
        validations.setStartSecondChar(2);
        validations.setEndFirstChar(3);
        validations.setEndSecondChar(4);

        assertFalse(validations.checkDiagonalMove());
    }

    @Test
    public void testValidForwardMovePlayer1() {
        Validations validations = new Validations();
        Player player = new Player(1, '⬤', 'o');
        validations.setCurrentPlayer(player);
        validations.setStartSecondChar(3);
        validations.setEndSecondChar(4);

        assertTrue(validations.isForwardMove());
    }

    @Test
    public void testValidForwardMovePlayer2() {
        Validations validations = new Validations();
        Player player = new Player(2, '╳', 'x');
        validations.setCurrentPlayer(player);
        validations.setStartSecondChar(4);
        validations.setEndSecondChar(3);

        assertTrue(validations.isForwardMove());
    }

}