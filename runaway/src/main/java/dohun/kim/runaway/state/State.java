package dohun.kim.runaway.state;

import com.squareup.javapoet.TypeName;

public class State {

    private final TypeName typeName;
    private final String name;

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
