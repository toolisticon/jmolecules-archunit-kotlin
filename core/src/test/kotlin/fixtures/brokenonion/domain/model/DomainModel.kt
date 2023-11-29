package io.toolisticon.jmolecules.archunit.fixtures.brokenonion.domain.model

import io.toolisticon.jmolecules.archunit.fixtures.brokenonion.infrastructure.InfrastructureThing
import org.jmolecules.architecture.onion.classical.DomainModelRing

@DomainModelRing
class DomainModel(
  val infra: InfrastructureThing
)
