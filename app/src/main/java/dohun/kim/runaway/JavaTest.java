package dohun.kim.runaway;

import dohun.kim.runaway.annotation.Container;

@Container(
        scopes = {MainActivity.class}
)
public interface JavaTest {

    String hello();

    String hello2();
}