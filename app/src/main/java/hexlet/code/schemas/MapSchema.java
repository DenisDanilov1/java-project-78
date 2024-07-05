package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema required() {
        Predicate<Map<String, String>> required = Objects::nonNull;
        checks.put("required", required);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map<String, String>> mapSize = map -> map.size() >= size;
        checks.put("sizeof", mapSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> shemas) {
       checks.put("shape", map -> {
            for (var entry : shemas.entrySet()) {
                String st = map.get(entry.getKey());
                if (!entry.getValue().isValid(st)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
