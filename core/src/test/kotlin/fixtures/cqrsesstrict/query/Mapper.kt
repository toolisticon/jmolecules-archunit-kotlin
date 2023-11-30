package io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.query

import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.event.SpecialThingsOccurred

interface Mapper {
  fun map(event: SpecialThingsOccurred): ThingEntity
}
