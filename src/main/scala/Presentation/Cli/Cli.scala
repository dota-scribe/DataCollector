package Presentation.Cli

import Core.Application.Port.Cli.CliPort

import scala.io.StdIn

// TODO: Inject IProCommandHandler

class Cli(cliPort: CliPort) {
    def Init(): Unit = {
        println("Welcome to the Dota Scribe Data Collector")
        println("You can find us at: https://github.com/dota-scribe/DataCollector")
        println("")
        println("Please select the data that you would like to collect:")
        println("1: Collect Pro Data (OpenDota)")
        println("")

        val selection = StdIn.readInt()

        selection match {
            case 1 => cliPort.CollectProData()
        }
    }
}
