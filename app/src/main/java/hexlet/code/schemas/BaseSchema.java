package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    private List<Predicate> conditions = new ArrayList<>();
    private boolean checking;

    protected final void addCondition(Predicate condition) {
        conditions.add(condition);
    }

    public final boolean isValid(Object obj) {
        if (obj == null) {
            return !checking;
        }
        for (Predicate condition : conditions) {
            if (!condition.test(obj)) {
                return false;
            }
        }
        return true;
    }

    protected final void setRequiredOn() {
        checking = true;
    }

    protected final void restart() {
        conditions.clear();
    }
}
