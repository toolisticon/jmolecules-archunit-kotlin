package io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.query

import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.event.SpecialThingsOccurred

interface Mapper {
  fun map(event: SpecialThingsOccurred): ThingEntity
}
