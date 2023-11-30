package io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.event

import org.jmolecules.event.annotation.DomainEvent

@DomainEvent
data class SpecialThingsOccurred(
  val value: String
)
