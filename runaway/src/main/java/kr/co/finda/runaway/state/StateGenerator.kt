package kr.co.finda.runaway.state

import kr.co.finda.runaway.getTypeName
import java.util.*
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement

fun generateState(element: Element, existStates: List<State>): State {
    val state = generateMethodState(element)

    val isAlreadyExistState = existStates.any { it.name == state.name }
    if (isAlreadyExistState) {
        throw IllegalStateException()
    }

    return state
}

private fun generateMethodState(element: Element): State {
    val typeName = (element as ExecutableElement).getTypeName()
    val name = element.simpleName.toString()
        .replace("get", "")
        .replace("set", "")
        .decapitalize(Locale.getDefault())

    return State(typeName, name)
}