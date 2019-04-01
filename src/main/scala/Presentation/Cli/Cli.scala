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
        println("2: Collect Pro Matches (OpenDota)")
        println("3: Get Match (OpenDota)")
        println("9: Regenerate DB Mappings")
        println("")
        print("> ")
//        cliPort.GetMatch(4595179473L)
        val selection = StdIn.readInt()

        selection match {
            case 1 => cliPort.CollectProData()
            case 2 => cliPort.GetProMatches()
            case 3 => GetMatch()
            case 9 => cliPort.RegenerateDbMappings()
        }
    }

    def GetMatch(): Unit = {
        println("Please enter match Id to retrieve")
        println("")
        print("> ")

        val selection = StdIn.readInt()
        cliPort.GetMatch(selection)
    }
}
