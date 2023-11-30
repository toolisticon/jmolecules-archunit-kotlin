package io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.query

import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.command.model.SpecialThingsEventSourcedCommandModel
import io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.event.SpecialThingsOccurred
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

  fun wrong(value: String): Boolean {
    return value == SpecialThingsEventSourcedCommandModel.CONST
  }
}
