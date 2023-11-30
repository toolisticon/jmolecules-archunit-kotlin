package io.toolisticon.jmolecules.archunit.fixtures.brokenhex.adapter.out

import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.adapter.SharedConstants.CONSTANT_VALUE
import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.port.out.OutMessage
import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.port.out.TechnologyOnOutPort
import org.jmolecules.architecture.hexagonal.SecondaryAdapter

@SecondaryAdapter(name = "Adapter for Tech")
class TechnologyAdapter() : TechnologyOnOutPort {

  override fun call(value: String): OutMessage {
    require(value == CONSTANT_VALUE) { "expected value to be $CONSTANT_VALUE"}
    TODO()
  }
}
