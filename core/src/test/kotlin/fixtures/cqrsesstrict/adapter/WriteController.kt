package io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.adapter

import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.command.api.DoSpecialThings
import io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.infra.CommandBus
import org.jmolecules.architecture.cqrs.CommandDispatcher

class WriteController(
  private val commandBus: CommandBus
) {

  @CommandDispatcher
  fun post() {
    commandBus.routeCommand(DoSpecialThings("value"))
  }
}
