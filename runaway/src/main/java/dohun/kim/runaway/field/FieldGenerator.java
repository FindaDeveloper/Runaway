package dohun.kim.runaway.field;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;

import dohun.kim.runaway.state.State;

public abstract class FieldGenerator {

    public State state;

    private FieldGenerator() {
    }

    public FieldGenerator(State state) {
        this.state = state;
    }

    public abstract FieldSpec getFieldSpec();

    public abstract MethodSpec getGetterMethodSpec();

    public abstract MethodSpec getSetterMethodSpec();
}
