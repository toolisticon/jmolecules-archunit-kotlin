package io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.command.model

import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.command.api.DoSpecialThings
import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.event.SpecialThingsOccurred
import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.infra.EventBus
import org.jmolecules.architecture.cqrs.CommandHandler
import org.jmolecules.event.annotation.DomainEventHandler
import org.jmolecules.event.annotation.DomainEventPublisher

class SpecialThingsEventSourcedCommandModel(
  private val eventBus: EventBus
) {

  private lateinit var value: String

  @CommandHandler
  @DomainEventPublisher
  fun decide(cmd: DoSpecialThings) {
    eventBus.dispatch(listOf(SpecialThingsOccurred(cmd.value)))
  }

  @DomainEventHandler
  fun evolve(event: Any) {
    when (event) {
      is SpecialThingsOccurred -> this.value = event.value
    }
  }
}
