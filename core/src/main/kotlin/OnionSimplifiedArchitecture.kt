package io.toolisticon.jmolecules.archunit

import com.tngtech.archunit.library.Architectures
import io.toolisticon.jmolecules.archunit.IsLayerType.Companion.layerType
import org.jmolecules.architecture.onion.simplified.ApplicationRing
import org.jmolecules.architecture.onion.simplified.DomainRing
import org.jmolecules.architecture.onion.simplified.InfrastructureRing

/**
 * Factory to create a simplified onion architecture rule.
 * [Onion Architecture](https://jeffreypalermo.com/tag/onion-architecture/)
 */
object OnionSimplifiedArchitecture {

  private const val ONION_SIMPLE_DOMAIN = "Domain"
  private const val ONION_SIMPLE_APPLICATION = "Application"
  private const val ONION_SIMPLE_INFRASTRUCTURE = "Infrastructure"

  /**
   * Returns simplified onion architecture rule.
   */
  @JvmStatic
  fun ensureOnionSimplified(packageMarkerSimpleClassNames: Set<String> = emptySet()): Architectures.LayeredArchitecture {
    return Architectures
      .layeredArchitecture()
      .consideringOnlyDependenciesInLayers()
      .withOptionalLayers(true)

      .layer(ONION_SIMPLE_INFRASTRUCTURE)
      .definedBy(
        layerType(
          definedByAnnotation = InfrastructureRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames,
        )
      )

      .layer(ONION_SIMPLE_APPLICATION)
      .definedBy(
        layerType(
          definedByAnnotation = ApplicationRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(ONION_SIMPLE_DOMAIN)
      .definedBy(
        layerType(
          definedByAnnotation = DomainRing::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )
      .whereLayer(ONION_SIMPLE_INFRASTRUCTURE).mayNotBeAccessedByAnyLayer()
      .whereLayer(ONION_SIMPLE_APPLICATION).mayOnlyBeAccessedByLayers(ONION_SIMPLE_INFRASTRUCTURE)
      .whereLayer(ONION_SIMPLE_DOMAIN)
      .mayOnlyBeAccessedByLayers(
        ONION_SIMPLE_APPLICATION,
        ONION_SIMPLE_INFRASTRUCTURE
      )

  }
}
