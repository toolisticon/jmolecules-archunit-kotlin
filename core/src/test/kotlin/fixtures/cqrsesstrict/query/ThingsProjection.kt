package io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.query

import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.event.SpecialThingsOccurred
import org.jmolecules.architecture.cqrs.QueryModel
import org.jmolecules.event.annotation.DomainEventHandler

@QueryModel
class ThingsProjection(
  private val repo: ThingsRepository,
  private val mapper: Mapper
) {

  fun query(id: String): ThingEntity {
    return repo.load(id)
  }

  @DomainEventHandler
  fun evolve(event: SpecialThingsOccurred) {
    this.repo.store(mapper.map(event))
  }
}
