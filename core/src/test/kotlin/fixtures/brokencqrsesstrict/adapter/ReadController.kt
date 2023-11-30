package io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.adapter

import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.command.api.DoSpecialThings
import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.command.model.SpecialThingsEventSourcedCommandModel
import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.query.ThingEntity
import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.query.ThingsProjection

class ReadController(
  private val projection: ThingsProjection,
  private val specialThingsEventSourcedCommandModel: SpecialThingsEventSourcedCommandModel
) {
  fun get(id: String): String {
    specialThingsEventSourcedCommandModel.decide(DoSpecialThings("value"))
    return map(projection.query(id))
  }

  private fun map(entity: ThingEntity): String {
    return entity.toString()
  }
}
