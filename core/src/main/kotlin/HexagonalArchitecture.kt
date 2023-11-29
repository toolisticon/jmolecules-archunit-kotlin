package io.toolisticon.jmolecules.archunit

import com.tngtech.archunit.library.Architectures
import io.toolisticon.jmolecules.archunit.IsLayerType.Companion.layerType
import org.jmolecules.architecture.hexagonal.*


/**
 * Factory to create a hexagonal architecture rule (aka port-adapters).
 * [Hexagon Architecture](https://alistair.cockburn.us/hexagonal-architecture/)
 */
object HexagonalArchitecture {

  private const val HEXAGONAL_APPLICATION = "Application"
  private const val HEXAGONAL_PORT = "Port"
  private const val HEXAGONAL_PORT_UNQUALIFIED = "Port (unqualified)"
  private const val HEXAGONAL_PRIMARY_PORT = "Primary port"
  private const val HEXAGONAL_SECONDARY_PORT = "Secondary port"
  private const val HEXAGONAL_ADAPTER = "Adapter"
  private const val HEXAGONAL_ADAPTER_UNQUALIFIED = "Adapter (unqualified)"
  private const val HEXAGONAL_PRIMARY_ADAPTER = "Primary adapter"
  private const val HEXAGONAL_SECONDARY_ADAPTER = "Secondary adapter"

  /**
   * Returns classical onion architecture rule.
   */
  @JvmStatic
  fun ensureHexagonal(packageMarkerSimpleClassNames: Set<String> = emptySet()): Architectures.LayeredArchitecture {
    return Architectures
      .layeredArchitecture()
      .consideringOnlyDependenciesInLayers()
      .withOptionalLayers(true)

      .layer(HEXAGONAL_APPLICATION)
      .definedBy(
        layerType(
          definedByAnnotation = Application::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(HEXAGONAL_PORT)
      .definedBy(
        layerType(
          definedByAnnotation = Port::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(HEXAGONAL_PORT_UNQUALIFIED)
      .definedBy(
        layerType(
          definedByAnnotation = Port::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        ).withExclusions(
          PrimaryPort::class.java,
          SecondaryPort::class.java
        )
      )

      .layer(HEXAGONAL_PRIMARY_PORT)
      .definedBy(
        layerType(
          definedByAnnotation = PrimaryPort::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(HEXAGONAL_SECONDARY_PORT)
      .definedBy(
        layerType(
          definedByAnnotation = SecondaryPort::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(HEXAGONAL_ADAPTER)
      .definedBy(
        layerType(
          definedByAnnotation = Adapter::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(HEXAGONAL_ADAPTER_UNQUALIFIED)
      .definedBy(
        layerType(
          definedByAnnotation = Adapter::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        ).withExclusions(
          PrimaryAdapter::class.java,
          SecondaryAdapter::class.java
        )
      )

      .layer(HEXAGONAL_PRIMARY_ADAPTER)
      .definedBy(
        layerType(
          definedByAnnotation = PrimaryAdapter::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(HEXAGONAL_SECONDARY_ADAPTER)
      .definedBy(
        layerType(
          definedByAnnotation = SecondaryAdapter::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .whereLayer(HEXAGONAL_PRIMARY_PORT)
      .mayOnlyBeAccessedByLayers(
        HEXAGONAL_APPLICATION,
        HEXAGONAL_PORT_UNQUALIFIED,
        HEXAGONAL_ADAPTER_UNQUALIFIED,
        HEXAGONAL_PRIMARY_ADAPTER
      )
      .whereLayer(HEXAGONAL_SECONDARY_PORT)
      .mayOnlyBeAccessedByLayers(
        HEXAGONAL_APPLICATION,
        HEXAGONAL_PORT_UNQUALIFIED,
        HEXAGONAL_ADAPTER_UNQUALIFIED,
        HEXAGONAL_SECONDARY_ADAPTER
      )
      .whereLayer(HEXAGONAL_PORT)
      .mayOnlyBeAccessedByLayers(HEXAGONAL_APPLICATION, HEXAGONAL_ADAPTER)

      .whereLayer(HEXAGONAL_ADAPTER_UNQUALIFIED)
      .mayOnlyBeAccessedByLayers(
        HEXAGONAL_PRIMARY_ADAPTER,
        HEXAGONAL_SECONDARY_ADAPTER
      )
      .whereLayer(HEXAGONAL_PRIMARY_ADAPTER)
      .mayOnlyBeAccessedByLayers(HEXAGONAL_ADAPTER_UNQUALIFIED)

      .whereLayer(HEXAGONAL_SECONDARY_ADAPTER)
      .mayOnlyBeAccessedByLayers(HEXAGONAL_ADAPTER_UNQUALIFIED)

      .whereLayer(HEXAGONAL_APPLICATION)
      .mayNotBeAccessedByAnyLayer()
  }

}
