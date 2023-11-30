package io.toolisticon.jmolecules.archunit

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import io.toolisticon.jmolecules.archunit.OnionArchitectureViolationsTest.Companion.PACKAGE
import io.toolisticon.jmolecules.archunit.OnionClassicalArchitecture.ensureOnionClassical
import io.toolisticon.jmolecules.archunit.OnionSimplifiedArchitecture.ensureOnionSimplified
import org.assertj.core.api.Assertions.assertThat

@AnalyzeClasses(packages = [PACKAGE])
class OnionArchitectureViolationsTest {

  companion object {
    const val PACKAGE = "io.toolisticon.jmolecules.archunit.fixtures.brokenonion"
  }

  @ArchTest
  fun `detect onion simplified violations`(classes: JavaClasses) {
    val result = ensureOnionSimplified(setOf("JMolecules")).evaluate(classes)
    assertThat(result.hasViolation()).isTrue()
    assertThat(result.failureReport.details).containsExactlyInAnyOrderElementsOf(
      listOf(
        "Constructor <$PACKAGE.application.service.ApplicationService.<init>($PACKAGE.domain.service.DomainService, $PACKAGE.infrastructure.InfrastructureThing)> has parameter of type <$PACKAGE.infrastructure.InfrastructureThing> in (ApplicationService.kt:0)",
        "Constructor <$PACKAGE.domain.model.DomainModel.<init>($PACKAGE.infrastructure.InfrastructureThing)> has parameter of type <$PACKAGE.infrastructure.InfrastructureThing> in (DomainModel.kt:0)",
        "Constructor <$PACKAGE.domain.service.DomainService.<init>($PACKAGE.application.service.ApplicationService)> has parameter of type <$PACKAGE.application.service.ApplicationService> in (DomainService.kt:0)",
        "Field <$PACKAGE.application.service.ApplicationService.infra> has type <$PACKAGE.infrastructure.InfrastructureThing> in (ApplicationService.kt:0)",
        "Field <$PACKAGE.domain.model.DomainModel.infra> has type <$PACKAGE.infrastructure.InfrastructureThing> in (DomainModel.kt:0)",
        "Field <$PACKAGE.domain.service.DomainService.applicationService> has type <$PACKAGE.application.service.ApplicationService> in (DomainService.kt:0)",
        "Method <$PACKAGE.domain.model.DomainModel.getInfra()> has return type <$PACKAGE.infrastructure.InfrastructureThing> in (DomainModel.kt:0)",
        "Method <$PACKAGE.domain.service.DomainService.getApplicationService()> has return type <$PACKAGE.application.service.ApplicationService> in (DomainService.kt:0)"
      )
    )
  }

  @ArchTest
  fun `detect onion classic violations`(classes: JavaClasses) {
    val result = ensureOnionClassical(setOf("JMolecules")).evaluate(classes)
    assertThat(result.hasViolation()).isTrue()
    assertThat(result.failureReport.details).containsExactlyInAnyOrderElementsOf(
      listOf(
        "Constructor <$PACKAGE.application.service.ApplicationService.<init>($PACKAGE.domain.service.DomainService, $PACKAGE.infrastructure.InfrastructureThing)> has parameter of type <$PACKAGE.infrastructure.InfrastructureThing> in (ApplicationService.kt:0)",
        "Constructor <$PACKAGE.domain.model.DomainModel.<init>($PACKAGE.infrastructure.InfrastructureThing)> has parameter of type <$PACKAGE.infrastructure.InfrastructureThing> in (DomainModel.kt:0)",
        "Constructor <$PACKAGE.domain.service.DomainService.<init>($PACKAGE.application.service.ApplicationService)> has parameter of type <$PACKAGE.application.service.ApplicationService> in (DomainService.kt:0)",
        "Field <$PACKAGE.application.service.ApplicationService.infra> has type <$PACKAGE.infrastructure.InfrastructureThing> in (ApplicationService.kt:0)",
        "Field <$PACKAGE.domain.model.DomainModel.infra> has type <$PACKAGE.infrastructure.InfrastructureThing> in (DomainModel.kt:0)",
        "Field <$PACKAGE.domain.service.DomainService.applicationService> has type <$PACKAGE.application.service.ApplicationService> in (DomainService.kt:0)",
        "Method <$PACKAGE.domain.model.DomainModel.getInfra()> has return type <$PACKAGE.infrastructure.InfrastructureThing> in (DomainModel.kt:0)",
        "Method <$PACKAGE.domain.service.DomainService.getApplicationService()> has return type <$PACKAGE.application.service.ApplicationService> in (DomainService.kt:0)"
      )
    )
  }
}
