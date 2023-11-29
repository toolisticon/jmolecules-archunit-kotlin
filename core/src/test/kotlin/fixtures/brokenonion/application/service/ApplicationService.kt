package io.toolisticon.jmolecules.archunit.fixtures.brokenonion.application.service

import io.toolisticon.jmolecules.archunit.fixtures.brokenonion.infrastructure.InfrastructureThing
import io.toolisticon.jmolecules.archunit.fixtures.brokenonion.domain.model.DomainModel
import io.toolisticon.jmolecules.archunit.fixtures.brokenonion.domain.service.DomainService
import org.jmolecules.architecture.onion.classical.ApplicationServiceRing

@ApplicationServiceRing
class ApplicationService(
  private val domainService: DomainService,
  private val infra: InfrastructureThing
) {

  fun call(domainModel: DomainModel) {
    domainService.loadModel(domainModel)
  }
}
