package io.toolisticon.jmolecules.archunit.fixtures.hex.port.out

import io.toolisticon.jmolecules.archunit.fixtures.hex.port.CrazyCommonConstantClass.Companion.HEADER_NAME
import org.jmolecules.architecture.hexagonal.SecondaryPort

@SecondaryPort(name = "This is access to technology")
interface TechnologyOnOutPort {
  @Write(HEADER_NAME)
  fun call(value: String)

  annotation class Write(
    val header: String
  )

}

