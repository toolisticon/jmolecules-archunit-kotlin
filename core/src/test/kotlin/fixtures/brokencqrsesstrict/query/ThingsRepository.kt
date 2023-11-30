package io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.query

interface ThingsRepository {
  fun load(id: String): ThingEntity
  fun store(entity: ThingEntity): String
}
