package dohun.kim.runaway.state;

import com.squareup.javapoet.TypeName;

import java.util.List;

import javax.lang.model.element.Element;

import dohun.kim.runaway.exception.AlreadyTakenStateException;

/**
 * {@link dohun.kim.runaway.annotation.Container}가 부착된 Java class의 필드에서 상태 정보를 얻을 때 사용됩니다.
 *
 * @author kimdohun
 * @see StateGenerator
 */
public class FieldElementStateGenerator extends StateGenerator {

    public FieldElementStateGenerator(Element element) {
        super(element);
    }

    /**
     * @param existStates 이미 존재하는 {@link State}인지 판단하기 위해서 사용됩니다.
     * @return {@link Element}에서 얻은 정보를 바탕으로 Container를 생성할 때 사용되는 {@link State} 형태로 가공합니다.
     * @throws AlreadyTakenStateException 이미 Container에서 해당 이름의 상태가 존재할 때 발생합니다
     */
    @Override
    public State generateState(List<State> existStates) throws AlreadyTakenStateException {
        Element element = getElement();

        TypeName typeName = TypeName.get(element.asType());
        String name = element.getSimpleName().toString();

        for (State state : existStates) {
            if (state.getName().equals(name)) {
                throw new AlreadyTakenStateException();
            }
        }

        return new State(typeName, name);
    }
}
