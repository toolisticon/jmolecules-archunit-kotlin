package io.toolisticon.jmolecules.archunit

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import io.toolisticon.jmolecules.archunit.HexagonalArchitecture.ensureHexagonal
import io.toolisticon.jmolecules.archunit.HexagonalArchitectureViolationsTest.Companion.PACKAGE
import io.toolisticon.jmolecules.archunit.OnionClassicalArchitecture.ensureOnionClassical
import io.toolisticon.jmolecules.archunit.OnionSimplifiedArchitecture.ensureOnionSimplified
import org.assertj.core.api.Assertions.assertThat

@AnalyzeClasses(packages = [PACKAGE])
class HexagonalArchitectureViolationsTest {

  companion object {
    const val PACKAGE = "io.toolisticon.jmolecules.archunit.fixtures.brokenhex"
  }

  @ArchTest
  fun `detect hexagonal violations`(classes: JavaClasses) {
    val result = ensureHexagonal(setOf("JMolecules")).evaluate(classes)
    assertThat(result.hasViolation()).isTrue()
    assertThat(result.failureReport.details).containsExactlyInAnyOrderElementsOf(
      listOf(
        "Method <$PACKAGE.adapter.in.DrivingAdapter.injectOut($PACKAGE.port.out.TechnologyOnOutPort)> has parameter of type <$PACKAGE.port.out.TechnologyOnOutPort> in (DrivingAdapter.kt:0)",
        "Method <$PACKAGE.application.MyInUseCase.transform($PACKAGE.adapter.in.DrivingDto)> calls method <$PACKAGE.adapter.in.DrivingDto.getValue()> in (MyInUseCase.kt:20)",
        "Method <$PACKAGE.application.MyInUseCase.transform($PACKAGE.adapter.in.DrivingDto)> has parameter of type <$PACKAGE.adapter.in.DrivingDto> in (MyInUseCase.kt:0)"
      )
    )
  }
}
