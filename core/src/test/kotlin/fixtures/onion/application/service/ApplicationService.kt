package io.toolisticon.jmolecules.archunit.fixtures.onion.application.service

import io.toolisticon.jmolecules.archunit.fixtures.onion.domain.model.DomainModel
import io.toolisticon.jmolecules.archunit.fixtures.onion.domain.service.DomainService
import org.jmolecules.architecture.onion.classical.ApplicationServiceRing

@ApplicationServiceRing
class ApplicationService(
  private val domainService: DomainService
) {

  fun call(domainModel: DomainModel) {
    domainService.loadModel(domainModel)
  }
}
