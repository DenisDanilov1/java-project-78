package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public final NumberSchema required() {
        Predicate<Integer> required = Objects::nonNull;
        checks.put("required", required);
        return this;
    }

    public final NumberSchema positive() {
        Predicate<Integer> pos = num -> num == null || num > 0;
        checks.put("positive", pos);
        return this;
    }

    public final NumberSchema range(int min, int max) {
        Predicate<Integer> range = num -> num != null && num >= min && num <= max;
        checks.put("range", range);
        return this;
    }
}
