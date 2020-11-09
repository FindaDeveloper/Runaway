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
    public FieldSpec generateFieldSpec() {
        return FieldSpec.builder(state.getTypeName().box(), state.getName())
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .addAnnotation(Nullable.class)
                .build();
    }

    @Override
    public MethodSpec generateGetterMethodSpec() {
        String getterName = "get" + StringUtil.getFirstUpperString(state.getName());
        return MethodSpec.methodBuilder(getterName)
                .addModifiers(Modifier.PUBLIC)
                .returns(state.getTypeName())
                .addAnnotation(Nullable.class)
                .addStatement("return " + state.getName())
                .build();
    }

    @Override
    public MethodSpec generateSetterMethodSpec() {
        String setterName = "set" + StringUtil.getFirstUpperString(state.getName());
        return MethodSpec.methodBuilder(setterName)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(state.getTypeName(), state.getName())
                .addStatement("this." + state.getName() + " = " + state.getName())
                .build();
    }

    @Override
    public MethodSpec generateGetOrDefaultMethodSpec() {
        String methodName = "get" + StringUtil.getFirstUpperString(state.getName()) + "OrDefault";
        return MethodSpec.methodBuilder(methodName)
                .returns(state.getTypeName().box())
                .addModifiers(Modifier.PUBLIC)
                .addParameter(state.getTypeName(), "defaultValue")
                .beginControlFlow("if (" + state.getName() + " == null)")
                .addStatement(state.getName() + " = defaultValue")
                .endControlFlow()
                .addStatement("return " + state.getName())
                .build();
    }
}
