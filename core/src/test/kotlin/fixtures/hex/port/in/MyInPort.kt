package io.toolisticon.jmolecules.archunit.fixtures.hex.port.`in`

import io.toolisticon.jmolecules.archunit.fixtures.hex.port.CrazyCommonConstantClass.Companion.HEADER_NAME
import org.jmolecules.architecture.hexagonal.PrimaryPort

@PrimaryPort(name = "This is access to application")
interface MyInPort {

  @Read(HEADER_NAME)
  fun invoke()

  annotation class Read(
    val header: String
  )

}
