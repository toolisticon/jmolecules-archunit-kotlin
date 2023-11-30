package io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.adapter

import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.query.ThingEntity
import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.query.ThingsProjection

class ReadController(
  private val projection: ThingsProjection
) {
  fun get(id: String): String {
    return map(projection.query(id))
  }

  private fun map(entity: ThingEntity): String {
    return entity.toString()
  }
}
