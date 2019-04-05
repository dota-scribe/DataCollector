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
        cliPort.GetMatch(4595179473L)
//        val selection = StdIn.readInt()
//
//        selection match {
//            case 1 => cliPort.CollectProData()
//            case 2 => cliPort.GetProMatches()
//            case 3 => GetMatch()
//            case 9 => cliPort.RegenerateDbMappings()
//        }
    }

    def GetMatch(): Unit = {
        println("Please enter match Id to retrieve")
        println("")
        print("> ")

//        val selection = StdIn.readInt()

        var matches = List(
            4610012482L,
            4610050063L,
            4610066727L,
            4610126604L,
            4610133550L,
            4610157690L,
            4610160338L,
            4610235417L,
            4610254465L,
            4610260304L,
        )

        matches.map(matchId => cliPort.GetMatch(matchId))


//        cliPort.GetMatch(selection)
    }
}
