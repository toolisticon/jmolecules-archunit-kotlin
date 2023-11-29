package io.toolisticon.jmolecules.archunit.fixtures.onion.infrastructure

import io.toolisticon.jmolecules.archunit.fixtures.onion.application.service.ApplicationService
import io.toolisticon.jmolecules.archunit.fixtures.onion.domain.model.DomainModel
import org.jmolecules.architecture.onion.simplified.InfrastructureRing

@InfrastructureRing
class InfrastructureThing(
  private val applicationService: ApplicationService
) {
  fun receive() {
    applicationService.call(DomainModel())
  }
}
