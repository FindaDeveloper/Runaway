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

    public abstract FieldSpec generateFieldSpec();

    public abstract MethodSpec generateGetterMethodSpec();

    public abstract MethodSpec generateSetterMethodSpec();
    
    public abstract MethodSpec generateGetOrDefaultMethodSpec();
}
