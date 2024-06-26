### Hexlet tests and linter status:
[![hexlet-check](https://github.com/DensiDanilov1/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/DensiDanilov1/java-project-78/actions/workflows/hexlet-check.yml)
[![Java CI](https://github.com/DensiDanilov1/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/DensiDanilov1/java-project-78/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/e376bb35c2920d555c95/maintainability)](https://codeclimate.com/github/DensiDanilov1/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/e376bb35c2920d555c95/test_coverage)](https://codeclimate.com/github/DensiDanilov1/java-project-78/test_coverage)

# Data validator
A data validator is a library with which you can check the correctness of data. At the moment, it is possible to check strings, numbers, and Map objects using validators.

## Usage examples:

### String validation
```
import hexlet.code.Validator; 
import hexlet.code.schemas.StringSchema;

Validator v = new Validator();

StringSchema schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(""); // false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// уже false, так как добавлена ещё одна проверка contains("whatthe")
```

### Validation of numbers
```
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator v = new Validator();

NumberSchema schema = v.number();

schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false

schema.positive().isValid(10); // true
schema.isValid(-10); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```
### Validation of Map type objects
```
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
```
### Nested validation
```
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

MapSchema schema = v.map();

// shape - allows you to describe validation for Map object values by keys.
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
```
