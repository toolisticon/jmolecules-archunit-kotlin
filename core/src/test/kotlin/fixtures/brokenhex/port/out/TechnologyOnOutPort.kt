package io.toolisticon.jmolecules.archunit.fixtures.brokenhex.port.out

import io.toolisticon.jmolecules.archunit.fixtures.hex.port.CrazyCommonConstantClass.Companion.HEADER_NAME
import org.jmolecules.architecture.hexagonal.SecondaryPort

interface TechnologyOnOutPort {
  @Write(HEADER_NAME)
  fun call(value: String): OutMessage

  annotation class Write(
    val header: String
  )

}

