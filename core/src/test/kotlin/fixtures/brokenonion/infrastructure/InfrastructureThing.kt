package io.toolisticon.jmolecules.archunit.fixtures.brokenonion.infrastructure

import io.toolisticon.jmolecules.archunit.fixtures.brokenonion.application.service.ApplicationService
import io.toolisticon.jmolecules.archunit.fixtures.brokenonion.domain.model.DomainModel
import org.jmolecules.architecture.onion.simplified.InfrastructureRing

@InfrastructureRing
class InfrastructureThing(
  private val applicationService: ApplicationService
) {
  fun receive() {
    applicationService.call(DomainModel(this))
  }
}
