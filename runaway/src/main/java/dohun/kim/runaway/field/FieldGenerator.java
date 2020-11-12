package dohun.kim.runaway.field;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Modifier;

import dohun.kim.runaway.state.State;
import dohun.kim.runaway.util.StringUtil;

/**
 * 상태에 대한 static 필드, getter/setter를 생성합니다.
 * @author kimdohun
 */
public class FieldGenerator {

    private final State state;

    /**
     * @param state 필드를 생성하기 위한 상태 정보를 파라미터로 받습니다.
     */
    public FieldGenerator(State state) {
        this.state = state;
    }

    /**
     * 원시 타입같은 경우엔 {@link Nullable}을 적용했을 때 Kotlin에서 null 대신 기본값으로 들어가는 이슈가 있었습니다.
     * 그래서 원시 타입은 {@link TypeName#box()}를 통해 WrapperClass로 변환합니다.
     * @return 상태의 타입, 이름을 통해 생성한 {@link FieldSpec}을 반환
     * @see FieldSpec
     */
    public FieldSpec generateFieldSpec() {
        return FieldSpec.builder(state.getTypeName().box(), state.getName())
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .addAnnotation(Nullable.class)
                .build();
    }

    /**
     * @return 생성된 필드에 대한 getter {@link MethodSpec}을 반환
     * @see MethodSpec
     */
    public MethodSpec generateGetterMethodSpec() {
        String getterName = "get" + StringUtil.getFirstUpperString(state.getName());
        return MethodSpec.methodBuilder(getterName)
                .addModifiers(Modifier.PUBLIC)
                .returns(state.getTypeName())
                .addAnnotation(Nullable.class)
                .addStatement("return " + state.getName())
                .build();
    }

    /**
     * @return 생성된 필드에 대한 setter {@link MethodSpec}을 반환
     * @see MethodSpec
     */
    public MethodSpec generateSetterMethodSpec() {
        String setterName = "set" + StringUtil.getFirstUpperString(state.getName());
        return MethodSpec.methodBuilder(setterName)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(state.getTypeName(), state.getName())
                .addStatement("this." + state.getName() + " = " + state.getName())
                .build();
    }

    /**
     * @return 데이터가 null이라면 전달받은 파라미터로 값을 설정한 후 반환하는 getOrDefault() {@link MethodSpec} 반환
     * @see MethodSpec
     */
    public MethodSpec generateGetOrDefaultMethodSpec() {
        String methodName = "get" + StringUtil.getFirstUpperString(state.getName()) + "OrDefault";
        return MethodSpec.methodBuilder(methodName)
                .returns(state.getTypeName().box())
                .addModifiers(Modifier.PUBLIC)
                .addParameter(
                        ParameterSpec
                                .builder(state.getTypeName(), "defaultValue")
                                .addAnnotation(NotNull.class)
                                .build()
                )
                .beginControlFlow("if (" + state.getName() + " == null)")
                .addStatement(state.getName() + " = defaultValue")
                .endControlFlow()
                .addStatement("return " + state.getName())
                .build();
    }
}
