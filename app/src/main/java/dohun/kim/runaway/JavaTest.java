package dohun.kim.runaway;

import dohun.kim.runaway.annotation.Container;

@Container(
        scopes = {
                FirstValueActivity.class,
                SecondValueActivity.class,
                ResultActivity.class
        }
)
public class JavaTest {

        private String string;

        private int intState;
}
