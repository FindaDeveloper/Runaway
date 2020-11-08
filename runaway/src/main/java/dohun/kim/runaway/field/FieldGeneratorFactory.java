package dohun.kim.runaway.field;

import dohun.kim.runaway.state.State;

public class FieldGeneratorFactory {

    public static FieldGenerator getFieldGenerator(State state) {
        return new DefaultFieldGenerator(state);
    }
}
