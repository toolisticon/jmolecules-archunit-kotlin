package io.toolisticon.jmolecules.archunit.fixtures.hex.adapter.out

import io.toolisticon.jmolecules.archunit.fixtures.hex.adapter.SharedConstants.CONSTANT_VALUE
import io.toolisticon.jmolecules.archunit.fixtures.hex.port.out.TechnologyOnOutPort
import org.jmolecules.architecture.hexagonal.SecondaryAdapter

@SecondaryAdapter(name = "Adapter for Tech")
class TechnologyAdapter : TechnologyOnOutPort {

  override fun call(value: String) {
    require(value == CONSTANT_VALUE) { "expected value to be $CONSTANT_VALUE"}
  }
}
