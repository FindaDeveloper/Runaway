package dohun.kim.runaway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Container 생성에 필요한 정보를 정의하기 위해서 사용됩니다.
 * <p>
 * 해당 어노테이션이 부착된 클래스 혹은 인터페이스에서 Field, Property 정보(변수명, 타입)를
 * 통해 {@link dohun.kim.runaway.state.State} 를 생성합니다.
 * @author kimdohun
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Container {

    /**
     * @return Container가 허용하는 생성 범위를 Class형태로 전닯합니다
     */
    Class<?>[] scopes();

    /**
     * @return 생성할 Container의 이름을 임의로 설정할 수 있습니다. 일반적으론 클래스 혹은 인터페이스의 이름 앞에 "Generated"를 붙인 형태로 생성됩니다.
     */
    String customContainerName() default "";
}
