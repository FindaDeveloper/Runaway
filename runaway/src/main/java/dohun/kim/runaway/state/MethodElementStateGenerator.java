package dohun.kim.runaway.state;

import com.squareup.javapoet.TypeName;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

import dohun.kim.runaway.exception.AlreadyTakenStateException;
import dohun.kim.runaway.util.StringUtil;

/**
 * Kotlin같은 경우엔 Field의 개념이 아닌 Property를 사용합니다. 자동으로 getter/setter를 생성하게 되는데,
 * 그 경우 {@link ExecutableElement} 형태로 나타납니다.
 * <p>
 * getter 혹은 setter {@link Element}에서 Container 생성에 필요한 정보를 담은 {@link State}정보를 취합니다.
 * @author kimdohun
 * @see StateGenerator
 */
public class MethodElementStateGenerator extends StateGenerator {

    public MethodElementStateGenerator(Element element) {
        super(element);
    }

    /**
     * @param existStates 이미 존재하는 {@link State}인지 판단하기 위해서 사용됩니다.
     * @return {@link Element}에서 얻은 정보를 바탕으로 Container를 생성할 때 사용되는 {@link State} 형태로 가공합니다.
     * @throws AlreadyTakenStateException 이미 Container에서 해당 이름의 상태가 존재할 때 발생합니다
     */
    @Override
    public State generateState(List<State> existStates) throws AlreadyTakenStateException {
        ExecutableElement element = (ExecutableElement) getElement();

        TypeMirror typeMirror = element.getReturnType();
        TypeName typeName = TypeName.get(typeMirror);

        String methodName = element.getSimpleName().toString();

        String name = StringUtil
                .getFirstLowerString(
                        methodName.replace("get", "")
                                .replace("set", "")
                );

        for (State state : existStates) {
            if (state.getName().equals(name)) {
                throw new AlreadyTakenStateException();
            }
        }

        return new State(typeName, name);
    }


}
