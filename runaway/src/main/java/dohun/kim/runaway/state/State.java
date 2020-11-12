package dohun.kim.runaway.state;

import com.squareup.javapoet.TypeName;

import java.util.Set;

import javax.annotation.processing.RoundEnvironment;

/**
 * Container 생성에 필요한 상태 정보를 담는 객체입니다.
 * {@link javax.annotation.processing.AbstractProcessor#process(Set, RoundEnvironment)}
 * 의 {@link RoundEnvironment}에서 {@link javax.lang.model.element.Element}를 통해 정보를 얻을 수 있습니다.
 * @author kimdohun
 */
public class State {

    private final TypeName typeName;
    private final String name;

    /**
     * @param typeName 상태가 가지는 타입을 나타냅니다.
     * @param name     상태의 이름을 나타냅니다. 생성된 Container에서 field의 이름입니다.
     */
    public State(TypeName typeName, String name) {
        this.typeName = typeName;
        this.name = name;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public String getName() {
        return name;
    }
}
