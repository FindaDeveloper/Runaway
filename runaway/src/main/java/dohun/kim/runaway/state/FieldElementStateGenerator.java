package dohun.kim.runaway.state;

import com.squareup.javapoet.TypeName;

import java.util.List;

import javax.lang.model.element.Element;

import dohun.kim.runaway.exception.AlreadyTakenStateException;

public class FieldElementStateGenerator extends StateGenerator {

    public FieldElementStateGenerator(Element element) {
        super(element);
    }

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
