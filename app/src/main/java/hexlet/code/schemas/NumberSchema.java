package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public final NumberSchema required() {
        addCondition(num -> num instanceof Integer && num != null);
        setRequiredOn();
        return this;
    }

    public final NumberSchema positive() {
        addCondition(num -> num instanceof Integer && Integer.parseInt(num.toString()) > 0);
        return this;
    }

    public final NumberSchema range(int min, int max) {
        addCondition(num -> num instanceof Integer && min <= Integer.parseInt(num.toString())
                && Integer.parseInt(num.toString()) <= max);
        return this;
    }
}
