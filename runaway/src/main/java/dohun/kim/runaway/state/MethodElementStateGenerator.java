package dohun.kim.runaway.state;

import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

import dohun.kim.runaway.util.StringUtil;

public class MethodElementStateGenerator extends StateGenerator {

    public MethodElementStateGenerator(Element element) {
        super(element);
    }

    @Override
    public State generateState() {
        ExecutableElement element = (ExecutableElement) getElement();

        TypeMirror typeMirror = element.getReturnType();
        TypeName typeName = TypeName.get(typeMirror);

        String getterName = element.getSimpleName().toString();
        String name = StringUtil.getFirstLowerString(getterName.replace("get", ""));

        return new State(typeName, name);
    }


}
