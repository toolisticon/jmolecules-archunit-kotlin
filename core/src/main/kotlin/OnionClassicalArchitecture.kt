package io.toolisticon.jmolecules.archunit

import com.tngtech.archunit.library.Architectures
import io.toolisticon.jmolecules.archunit.IsLayerType.Companion.layerType
import org.jmolecules.architecture.onion.classical.ApplicationServiceRing
import org.jmolecules.architecture.onion.classical.DomainModelRing
import org.jmolecules.architecture.onion.classical.DomainServiceRing
import org.jmolecules.architecture.onion.classical.InfrastructureRing

/**
 * Factory to create a classical onion architecture rule.
 * [Onion Architecture](https://jeffreypalermo.com/tag/onion-architecture/)
 */
object OnionClassicalArchitecture {

  private const val ONION_CLASSICAL_DOMAIN_MODEL = "Domain model"
  private const val ONION_CLASSICAL_DOMAIN_SERVICE = "Domain service"
  private const val ONION_CLASSICAL_APPLICATION = "Application"
  private const val ONION_CLASSICAL_INFRASTRUCTURE = "Infrastructure"


  /**
   * Returns classical onion architecture rule.
   */
  fun ensureOnion(packageMarkerSimpleClassNames: Set<String> = emptySet()): Architectures.OnionArchitecture {
    return Architectures
      .onionArchitecture()
      .withOptionalLayers(true)

      .adapter(ONION_CLASSICAL_INFRASTRUCTURE,
        layerType(
          definedByAnnotation = InfrastructureRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .applicationServices(
        layerType(
          definedByAnnotation = ApplicationServiceRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .domainServices(
        layerType(
          definedByAnnotation = DomainServiceRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .domainModels(
        layerType(
          definedByAnnotation = DomainModelRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )
  }

  /**
   * Returns classical onion architecture rule.
   */
  @JvmStatic
  fun ensureOnionClassical(packageMarkerSimpleClassNames: Set<String> = emptySet()): Architectures.LayeredArchitecture {
    return Architectures
      .layeredArchitecture()
      .consideringOnlyDependenciesInLayers()
      .withOptionalLayers(true)

      .layer(ONION_CLASSICAL_INFRASTRUCTURE)
      .definedBy(
        layerType(
          definedByAnnotation = InfrastructureRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(ONION_CLASSICAL_APPLICATION)
      .definedBy(
        layerType(
          definedByAnnotation = ApplicationServiceRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(ONION_CLASSICAL_DOMAIN_SERVICE)
      .definedBy(
        layerType(
          definedByAnnotation = DomainServiceRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(ONION_CLASSICAL_DOMAIN_MODEL)
      .definedBy(
        layerType(
          definedByAnnotation = DomainModelRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .addPatternConstraints()
  }

  private fun Architectures.LayeredArchitecture.addPatternConstraints() =
    this.whereLayer(ONION_CLASSICAL_INFRASTRUCTURE).mayNotBeAccessedByAnyLayer()
      .whereLayer(ONION_CLASSICAL_APPLICATION).mayOnlyBeAccessedByLayers(ONION_CLASSICAL_INFRASTRUCTURE)
      .whereLayer(ONION_CLASSICAL_DOMAIN_SERVICE).mayOnlyBeAccessedByLayers(
        ONION_CLASSICAL_APPLICATION,
        ONION_CLASSICAL_INFRASTRUCTURE
      )
      .whereLayer(ONION_CLASSICAL_DOMAIN_MODEL).mayOnlyBeAccessedByLayers(
        ONION_CLASSICAL_DOMAIN_SERVICE,
        ONION_CLASSICAL_APPLICATION,
        ONION_CLASSICAL_INFRASTRUCTURE
      )
}
