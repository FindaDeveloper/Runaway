package dohun.kim.runaway.state;

import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;

public class FieldElementStateGenerator extends StateGenerator {

    public FieldElementStateGenerator(Element element) {
        super(element);
    }

    @Override
    public State generateState() {
        Element element = getElement();

        TypeName typeName = TypeName.get(element.asType());
        String name = element.getSimpleName().toString();

        return new State(typeName, name);
    }
}
