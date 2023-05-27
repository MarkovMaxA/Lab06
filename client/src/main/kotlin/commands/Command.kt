package client.commands

import common.net.requests.*

/**
 * Execution command representative interface
 */
abstract class Command {
    abstract fun getName(): String

    abstract fun execute(argument: String?): Request
}