package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    @Test
    public void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        final int numTrue = 2;
        final int numFalse = 9;

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertFalse(schema.contains("what the").isValid("what does the fox say"));
        assertFalse(schema.isValid("what does the fox say"));
        assertTrue(schema.minLength(numTrue).isValid("what the"));
        assertFalse(schema.minLength(numFalse).isValid("what the"));
    }

}
