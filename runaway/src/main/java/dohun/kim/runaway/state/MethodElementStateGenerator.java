package dohun.kim.runaway.state;

import com.squareup.javapoet.TypeName;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

import dohun.kim.runaway.exception.AlreadyTakenStateException;
import dohun.kim.runaway.util.StringUtil;

public class MethodElementStateGenerator extends StateGenerator {

    public MethodElementStateGenerator(Element element) {
        super(element);
    }

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
