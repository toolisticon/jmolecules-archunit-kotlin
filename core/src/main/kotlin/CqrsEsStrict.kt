package io.toolisticon.jmolecules.archunit

import com.tngtech.archunit.library.Architectures
import io.toolisticon.jmolecules.archunit.IsLayerType.Companion.layerType
import org.jmolecules.architecture.cqrs.Command
import org.jmolecules.architecture.cqrs.CommandDispatcher
import org.jmolecules.architecture.cqrs.CommandHandler
import org.jmolecules.architecture.cqrs.QueryModel
import org.jmolecules.event.annotation.DomainEvent
import org.jmolecules.event.annotation.DomainEventHandler
import org.jmolecules.event.annotation.DomainEventPublisher

object CqrsEsStrict {

  private const val CQRS_COMMAND_DISPATCHER = "Command dispatcher"
  private const val CQRS_COMMAND_HANDLER = "Command handler"
  private const val CQRS_COMMAND = "Command"
  private const val CQRS_DOMAIN_EVENT = "Domain Event"
  private const val CQRS_DOMAIN_EVENT_PUBLISHER = "Domain Event Publisher"
  private const val CQRS_DOMAIN_EVENT_HANDLER = "Domain Event Handler"
  private const val CQRS_QUERY_MODEL = "Query model"


  fun ensureCqrsStrict(packageMarkerSimpleClassNames: Set<String> = emptySet()): Architectures.LayeredArchitecture {
    return Architectures
      .layeredArchitecture()
      .consideringOnlyDependenciesInLayers()
      .withOptionalLayers(true)

      .layer(CQRS_COMMAND_DISPATCHER)
      .definedBy(
        layerType(
          definedByAnnotation = CommandDispatcher::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(CQRS_COMMAND_HANDLER)
      .definedBy(
        layerType(
          definedByAnnotation = CommandHandler::class.java, // FIXME -> Bullshit, the handler is a method inside a class
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(CQRS_QUERY_MODEL)
      .definedBy(
        layerType(
          definedByAnnotation = QueryModel::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(CQRS_COMMAND)
      .definedBy(
        layerType(
          definedByAnnotation = Command::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(CQRS_DOMAIN_EVENT)
      .definedBy(
        layerType(
          definedByAnnotation = DomainEvent::class.java,
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(CQRS_DOMAIN_EVENT_PUBLISHER)
      .definedBy(
        layerType(
          definedByAnnotation = DomainEventPublisher::class.java, // FIXME: Bullshit, publisher is a method inside a class
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .layer(CQRS_DOMAIN_EVENT_HANDLER)
      .definedBy(
        layerType(
          definedByAnnotation = DomainEventHandler::class.java, // FIXME: Bullshit, publisher is a method inside a class
          packageMarkerSimpleClassNames = packageMarkerSimpleClassNames
        )
      )

      .whereLayer(CQRS_COMMAND_HANDLER) // no direct references to command handlers
      .mayNotBeAccessedByAnyLayer()

      .whereLayer(CQRS_QUERY_MODEL) // no direct references to query models
      .mayNotBeAccessedByAnyLayer()

      .whereLayer(CQRS_COMMAND) // command is shared between dispatcher and handler
      .mayOnlyBeAccessedByLayers(CQRS_COMMAND_HANDLER, CQRS_COMMAND_DISPATCHER)

      .whereLayer(CQRS_DOMAIN_EVENT) // event is shared between command model and query model or used by regular event publishers or handlers
      .mayOnlyBeAccessedByLayers(CQRS_QUERY_MODEL, CQRS_COMMAND_HANDLER, CQRS_DOMAIN_EVENT_HANDLER, CQRS_DOMAIN_EVENT_PUBLISHER)

  }
}
