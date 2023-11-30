package io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.infra

interface EventBus {

  fun dispatch(events: List<Any>)
}
