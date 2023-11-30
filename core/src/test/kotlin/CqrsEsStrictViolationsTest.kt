package io.toolisticon.jmolecules.archunit

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import io.toolisticon.jmolecules.archunit.CqrsEsStrict.ensureCqrsEsStrict
import io.toolisticon.jmolecules.archunit.CqrsEsStrictViolationsTest.Companion.PACKAGE
import io.toolisticon.jmolecules.archunit.HexagonalArchitecture.ensureHexagonal
import io.toolisticon.jmolecules.archunit.OnionClassicalArchitecture.ensureOnionClassical
import io.toolisticon.jmolecules.archunit.OnionSimplifiedArchitecture.ensureOnionSimplified
import org.assertj.core.api.Assertions.assertThat

@AnalyzeClasses(packages = [PACKAGE])
class CqrsEsStrictViolationsTest {

  companion object {
    const val PACKAGE = "io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict"
  }

  @ArchTest
  fun `detects cqrs es strict violations`(classes: JavaClasses) {
    val result = ensureCqrsEsStrict().evaluate(classes)
    assertThat(result.hasViolation()).isTrue()
    assertThat(result.failureReport.details).containsExactlyInAnyOrderElementsOf(
      listOf(
        "Constructor <$PACKAGE.adapter.WriteController.<init>($PACKAGE.command.model.SpecialThingsEventSourcedCommandModel, io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.query.ThingsProjection)> has parameter of type <$PACKAGE.command.model.SpecialThingsEventSourcedCommandModel> in (WriteController.kt:0)",
        "Field <$PACKAGE.adapter.WriteController.specialThingsEventSourcedCommandModel> has type <$PACKAGE.command.model.SpecialThingsEventSourcedCommandModel> in (WriteController.kt:0)",
        "Method <$PACKAGE.adapter.WriteController.post()> calls method <$PACKAGE.command.model.SpecialThingsEventSourcedCommandModel.decide($PACKAGE.command.api.DoSpecialThings)> in (WriteController.kt:17)",
        "Method <$PACKAGE.event.SpecialThingsOccurred.fromCommand($PACKAGE.command.api.DoSpecialThings)> has parameter of type <$PACKAGE.command.api.DoSpecialThings> in (SpecialThingsOccurred.kt:0)",
        "Method <$PACKAGE.event.SpecialThingsOccurred.inCommandModel($PACKAGE.command.model.SpecialThingsEventSourcedCommandModel)> has parameter of type <$PACKAGE.command.model.SpecialThingsEventSourcedCommandModel> in (SpecialThingsOccurred.kt:0)",
        "Method <$PACKAGE.event.SpecialThingsOccurred.apply($PACKAGE.query.ThingsProjection)> has parameter of type <$PACKAGE.query.ThingsProjection> in (SpecialThingsOccurred.kt:0)"
      )
    )
  }
}
