package io.toolisticon.jmolecules.archunit.fixtures.brokencqrsesstrict.command.api

import org.jmolecules.architecture.cqrs.Command

@Command
class DoSpecialThings(
  val value: String
)
