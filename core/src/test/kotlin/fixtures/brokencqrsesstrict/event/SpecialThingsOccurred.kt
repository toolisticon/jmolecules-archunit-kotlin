package io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.event

import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.command.api.DoSpecialThings
import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.command.model.SpecialThingsEventSourcedCommandModel
import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.query.ThingsProjection
import org.jmolecules.event.annotation.DomainEvent

@DomainEvent
data class SpecialThingsOccurred(
  val value: String
) {
  fun apply(queryModel: ThingsProjection) {

  }

  fun fromCommand(command: DoSpecialThings) {

  }

  fun inCommandModel(specialThingsEventSourcedCommandModel: SpecialThingsEventSourcedCommandModel) {

  }
}
