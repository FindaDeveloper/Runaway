package dohun.kim.runaway.state;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class StateGeneratorFactory {

    public static StateGenerator getStateGenerator(Element element) {
        ElementKind elementKind = element.getKind();

        if (elementKind == ElementKind.FIELD) {
            return new FieldElementStateGenerator(element);
        }

        if (elementKind == ElementKind.METHOD) {
            return new MethodElementStateGenerator(element);
        }

        throw new IllegalStateException();
    }
}
