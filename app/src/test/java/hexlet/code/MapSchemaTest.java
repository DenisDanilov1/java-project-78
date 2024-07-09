package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class MapSchemaTest {

    @Test
    public void testRequired() {
        Validator val = new Validator();
        MapSchema mapSchema = val.map();

        assertTrue(mapSchema.isValid(null));
        assertTrue(mapSchema.required().isValid(new HashMap<>()));
        assertFalse(mapSchema.isValid(null));
        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(mapSchema.isValid(data));
    }
    @Test
    public void testSizeOf() {
        Validator val = new Validator();
        MapSchema mapSchema = val.map();

        assertTrue(mapSchema.sizeof(0).isValid(new HashMap<>()));
        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        assertTrue(mapSchema.sizeof(1).isValid(data));
        assertFalse(mapSchema.sizeof(3).isValid(data));
    }
    @Test
    public void testShape() {
        Validator val = new Validator();
        MapSchema mapSchema = val.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", val.string().required());
        schemas.put("lastName", val.string().required().minLength(2));
        mapSchema.shape(schemas);
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(mapSchema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(mapSchema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(mapSchema.isValid(human3));
    }
}
