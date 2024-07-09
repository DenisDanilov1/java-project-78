package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    @Test
    public void testRequired() {
        Validator val = new Validator();
        NumberSchema n = val.number();

        assertTrue(n.isValid(null));
        assertFalse(n.required().isValid(null));
    }
    @Test
    public void testPositive() {
        Validator val = new Validator();
        NumberSchema n = val.number();

        assertTrue(n.positive().isValid(5));
        assertFalse(n.positive().isValid(-1));
    }
    @Test
    public void testRrange() {
        Validator val = new Validator();
        NumberSchema n = val.number();

        assertTrue(n.range(1, 9).isValid(5));
        assertFalse(n.range(1, 3).isValid(5));
    }
    @Test
    public void testAccumulation() {
        Validator val = new Validator();
        NumberSchema n = val.number();

        n.required();
        assertFalse(n.isValid(null));
        assertTrue(n.isValid(7));
        assertTrue(n.isValid(-1));
        n.positive();
        n.range(2, 4);
        assertTrue(n.isValid(2));
        assertFalse(n.isValid(1));
    }
    @Test
    public void testAll() {
        Validator val = new Validator();
        NumberSchema n = val.number();

        assertTrue(n.required().positive().range(2, 9).isValid(7));
        assertFalse(n.required().positive().range(5, 10).isValid(2));
        assertFalse(n.required().positive().range(5, 10).isValid(-1));
    }
}
