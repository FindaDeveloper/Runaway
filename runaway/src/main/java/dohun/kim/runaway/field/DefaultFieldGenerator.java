package dohun.kim.runaway.field;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;

import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Modifier;

import dohun.kim.runaway.state.State;
import dohun.kim.runaway.util.StringUtil;

public class DefaultFieldGenerator extends FieldGenerator {
    public DefaultFieldGenerator(State state) {
        super(state);
    }

    @Override
    public FieldSpec getFieldSpec() {
        return FieldSpec.builder(state.getTypeName(), state.getName())
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .addAnnotation(Nullable.class)
                .build();
    }

    @Override
    public MethodSpec getGetterMethodSpec() {
        String getterName = "get" + StringUtil.getFirstUpperString(state.getName());
        return MethodSpec.methodBuilder(getterName)
                .addModifiers(Modifier.PUBLIC)
                .returns(state.getTypeName())
                .addAnnotation(Nullable.class)
                .addStatement("return " + state.getName())
                .build();
    }

    @Override
    public MethodSpec getSetterMethodSpec() {
        String setterName = "set" + StringUtil.getFirstUpperString(state.getName());
        return MethodSpec.methodBuilder(setterName)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("this." + state.getName() + " = " + state.getName())
                .build();
    }


}
