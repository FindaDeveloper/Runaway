package dohun.kim.runaway.state;

import javax.lang.model.element.Element;

public abstract class StateGenerator {

    private Element element;

    private StateGenerator() {
    }

    public StateGenerator(Element element) {
        this.element = element;
    }

    public abstract State generateState();

    public Element getElement() {
        return element;
    }
}
