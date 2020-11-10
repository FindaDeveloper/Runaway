package dohun.kim.runaway.state;

import java.util.List;

import javax.lang.model.element.Element;

import dohun.kim.runaway.exception.AlreadyTakenStateException;

public abstract class StateGenerator {

    private Element element;

    private StateGenerator() {
    }

    public StateGenerator(Element element) {
        this.element = element;
    }

    public abstract State generateState(List<State> existStates) throws AlreadyTakenStateException;

    public Element getElement() {
        return element;
    }
}
