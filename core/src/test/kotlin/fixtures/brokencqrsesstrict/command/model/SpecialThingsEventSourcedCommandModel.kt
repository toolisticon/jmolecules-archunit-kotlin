package io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.command.model

import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.command.api.DoSpecialThings
import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.event.SpecialThingsOccurred
import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.infra.EventBus
import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.query.ThingsProjection
import org.jmolecules.architecture.cqrs.CommandHandler
import org.jmolecules.event.annotation.DomainEventHandler
import org.jmolecules.event.annotation.DomainEventPublisher

class SpecialThingsEventSourcedCommandModel(
  private val eventBus: EventBus,
  private val projection: ThingsProjection
) {
  companion object {
    const val CONST = "42"
  }

  private lateinit var value: String

  @CommandHandler
  @DomainEventPublisher
  fun decide(cmd: DoSpecialThings) {
    if (projection.query(cmd.value).toString() == cmd.value) {
      eventBus.dispatch(listOf(SpecialThingsOccurred(cmd.value)))
    }
  }

  @DomainEventHandler
  fun evolve(event: Any) {
    when (event) {
      is SpecialThingsOccurred -> this.value = event.value
    }
  }

}
