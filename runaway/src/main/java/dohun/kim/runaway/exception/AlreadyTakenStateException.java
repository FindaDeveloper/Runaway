package dohun.kim.runaway.exception;

/**
 * 이미 생성된 {@link dohun.kim.runaway.state.State}가 존재할 때 {@link dohun.kim.runaway.state.StateGenerator}
 * 에서 해당 예외를 던집니다.
 * <p>
 * 해당 예외가 발생하는 대표적인 경우는 Kotlin을 사용할 때 발생합니다. Kotlin의 property를 사용할 때 getter/setter 모두 생성되는
 * 경우가 있습니다.
 * @author kimdohun
 */
public class AlreadyTakenStateException extends Exception {
}
