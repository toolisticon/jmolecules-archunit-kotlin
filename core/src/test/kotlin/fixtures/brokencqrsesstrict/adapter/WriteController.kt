package io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.adapter

import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.command.api.DoSpecialThings
import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.command.model.SpecialThingsEventSourcedCommandModel
import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.infra.CommandBus
import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.query.ThingsProjection
import org.jmolecules.architecture.cqrs.CommandDispatcher

class WriteController(
  private val specialThingsEventSourcedCommandModel: SpecialThingsEventSourcedCommandModel,
  private val thingsProjection: ThingsProjection
) {

  @CommandDispatcher
  fun post() {
    thingsProjection.query("value")
    specialThingsEventSourcedCommandModel.decide(DoSpecialThings("value"))
  }
}
