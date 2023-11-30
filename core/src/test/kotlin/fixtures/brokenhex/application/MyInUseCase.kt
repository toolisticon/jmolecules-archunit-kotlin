package io.toolisticon.jmolecules.archunit.fixtures.brokenhex.application

import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.adapter.`in`.DrivingAdapter
import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.adapter.`in`.DrivingDto
import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.port.`in`.MyInPort
import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.port.out.OutMessage
import io.toolisticon.jmolecules.archunit.fixtures.brokenhex.port.out.TechnologyOnOutPort
import org.jmolecules.architecture.hexagonal.Application

@Application
class MyInUseCase(
  private val technologyOnOutPort: TechnologyOnOutPort
): MyInPort {

  override fun invoke() {
    technologyOnOutPort.call("value")
  }

  fun transform(dto: DrivingDto): OutMessage {
    return OutMessage(dto.value)
  }
}
