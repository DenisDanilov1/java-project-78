package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    public StringSchema required() {
        Predicate<String> required = value -> value != null && !value.isEmpty();
        checks.put("required", required);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> minLen = str -> str == null || str.length() >= length;
        checks.put("minLength", minLen);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> containsPredicate = str -> str == null || str.contains(substring);
        checks.put("contains", containsPredicate);
        return this;
    }
}
