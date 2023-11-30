package io.toolisticon.jmolecules.archunit.fixtures.brokenhex.adapter.`in`

import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.adapter.SharedConstants.CONSTANT_VALUE
import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.port.`in`.MyInPort
import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.port.out.TechnologyOnOutPort
import org.jmolecules.architecture.hexagonal.PrimaryAdapter

class DrivingAdapter(
  private val myInPort: MyInPort,
) {

  fun infrastructureWillCallThis() {
    myInPort.invoke()
    println(CONSTANT_VALUE)
  }

  fun injectOut(technologyOnOutPort: TechnologyOnOutPort) {

  }

}
