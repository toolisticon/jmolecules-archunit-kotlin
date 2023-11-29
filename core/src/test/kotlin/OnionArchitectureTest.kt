package io.toolisticon.jmolecules.archunit

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import io.toolisticon.jmolecules.archunit.OnionClassicalArchitecture.ensureOnionClassical
import io.toolisticon.jmolecules.archunit.OnionSimplifiedArchitecture.ensureOnionSimplified
import org.assertj.core.api.Assertions.assertThat

@AnalyzeClasses(packages = ["io.toolisticon.jmolecules.archunit.fixtures.onion"])
class OnionArchitectureTest {

  @ArchTest
  fun `checks onion simplified`(classes: JavaClasses) {
    val result = ensureOnionSimplified(setOf("JMolecules")).evaluate(classes)
    assertThat(result.hasViolation()).isFalse()
  }

  @ArchTest
  fun `checks onion classic`(classes: JavaClasses) {
    val result = ensureOnionClassical(setOf("JMolecules")).evaluate(classes)
    assertThat(result.hasViolation()).isFalse()
  }
}
