package io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.infra

interface EventBus {

  fun dispatch(events: List<Any>)
}
