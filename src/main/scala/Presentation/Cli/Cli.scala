package Presentation.Cli

import Core.Application.Port.Cli.CliPort

import scala.io.StdIn

class Cli(cliPort: CliPort) {

    def Init(): Unit = {
        print("\033[2J")
        println("Welcome to the Dota Scribe Data Collector")
        println("You can find us at: https://github.com/dota-scribe/DataCollector")
        println("")
        println("Please select the data that you would like to collect:")
        println("1: Collect Pro Matches and Players (OpenDota)")
        println("2: Collect Pro Matches (OpenDota)")
        println("3: Get Match (OpenDota)")
        println("4: Sync ProMatch meta data")
        println("5: Load Pro Player Batches")
        println("6: Collect Premium Matches (DatDota)")
        println("7: Sync Premium Match Data")
        print("> ")

        val selection = StdIn.readInt()

        selection match {
            case 1 => cliPort.CollectProData()
            case 2 => cliPort.CollectPromatchesFromOpenDota()
            case 3 => GetMatch()
            case 4 => cliPort.CollectProMatchesInDb()
            case 5 => GetNumBatches()
            case 6 => cliPort.CollectPremiumMatches()
            case 7 => cliPort.CollectPremiumMatchesInDb()
        }
    }

    def GetMatch(): Unit = {
        print("\033[2J")
        println("Please enter match Id to retrieve")
        println("")
        print("> ")

        val selection = StdIn.readInt()
        cliPort.CollectMatch(selection)
    }

    def GetNumBatches(): Unit = {
        print("\033[2J")
        println("Pro matches are retrieved in batches of 100, please enter the number of batches you would like to collect:")
        println("")
        print("> ")

        val selection = StdIn.readInt()
        cliPort.LoadProMatchBatch(selection)
    }
}
