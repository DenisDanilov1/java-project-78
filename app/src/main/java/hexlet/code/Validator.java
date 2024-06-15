package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    public final StringSchema string() {
        return new StringSchema();
    }

    public final NumberSchema number() {
        return new NumberSchema();
    }

    public final MapSchema map() {
        return new MapSchema();
    }
}
