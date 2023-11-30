package io.toolisticon.jmolecules.archunit.fixtures.cqrsesstrict.command.api

import org.jmolecules.architecture.cqrs.Command

@Command
class DoSpecialThings(
  val value: String
)
