package io.toolisticon.jmolecules.archunit

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import io.toolisticon.jmolecules.archunit.CqrsEsStrict.ensureCqrsEsStrict
import io.toolisticon.jmolecules.archunit.HexagonalArchitecture.ensureHexagonal
import io.toolisticon.jmolecules.archunit.OnionClassicalArchitecture.ensureOnionClassical
import io.toolisticon.jmolecules.archunit.OnionSimplifiedArchitecture.ensureOnionSimplified
import org.assertj.core.api.Assertions.assertThat

@AnalyzeClasses(packages = ["io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict"])
class CqrsEsStrictTest {

  @ArchTest
  fun `checks cqrs es strict`(classes: JavaClasses) {
    val result = ensureCqrsEsStrict().evaluate(classes)
    assertThat(result.hasViolation()).isFalse()
  }
}
