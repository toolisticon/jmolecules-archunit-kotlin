package io.toolisticon.jmolecules.archunit.fixtures.hex.application

import io.toolisticon.jmolecules.archunit.fixtures.hex.port.`in`.MyInPort
import io.toolisticon.jmolecules.archunit.fixtures.hex.port.out.TechnologyOnOutPort
import org.jmolecules.architecture.hexagonal.Application

@Application
class MyInUseCase(
  private val technologyOnOutPort: TechnologyOnOutPort
): MyInPort {

  override fun invoke() {
    technologyOnOutPort.call("value")
  }
}
