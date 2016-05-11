package runnables

import servers.http.DebugHttpServer

object Servers {
    @JvmStatic fun main(args: Array<String>) {
        println("Running Servers");

        val debugServer = DebugHttpServer("/api/debug", 11111)
        debugServer.start()
        println("Server started")

        println("Press Enter to exit")
        readLine()
        println("Servers Finished")
    }
}
