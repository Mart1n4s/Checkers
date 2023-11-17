import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    public void testValidCoordinate() {
        Coordinates coordinates = new Coordinates();
        assertTrue(coordinates.checkCoordinate("b3"));
    }

    @Test
    public void testInvalidCoordinate() {
        Coordinates coordinates = new Coordinates();
        assertFalse(coordinates.checkCoordinate("1b"));
    }

}