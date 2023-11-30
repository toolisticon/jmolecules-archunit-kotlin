package io.toolisticon.jmolecules.archunit.fixtures.hex.adapter.`in`

import io.toolisticon.jmolecules.archunit.fixtures.hex.adapter.SharedConstants.CONSTANT_VALUE
import io.toolisticon.jmolecules.archunit.fixtures.hex.port.`in`.MyInPort
import org.jmolecules.architecture.hexagonal.PrimaryAdapter

@PrimaryAdapter(name = "Message Handler")
class DrivingAdapter(
  private val myInPort: MyInPort
) {

  fun infrastructureWillCallThis() {
    myInPort.invoke()
    println(CONSTANT_VALUE)
  }

}
