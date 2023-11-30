package io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.infra

import java.util.concurrent.CompletableFuture

interface CommandBus {
  fun routeCommand(command: Any): CompletableFuture<Boolean>
}
