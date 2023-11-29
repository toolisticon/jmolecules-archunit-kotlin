package io.toolisticon.jmolecules.archunit.fixtures.brokenonion.domain.service

import io.toolisticon.jmolecules.archunit.fixtures.brokenonion.application.service.ApplicationService
import io.toolisticon.jmolecules.archunit.fixtures.brokenonion.domain.model.DomainModel
import org.jmolecules.architecture.onion.classical.DomainServiceRing

@DomainServiceRing
class DomainService(
  val applicationService: ApplicationService
) {

  fun loadModel(model: DomainModel) {

  }
}
