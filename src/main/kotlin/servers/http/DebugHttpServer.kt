package servers.http

import com.google.gson.Gson
import framework.terrain.maps.HeightMap
import org.glassfish.grizzly.http.server.*
import org.glassfish.grizzly.http.util.HttpStatus
import utils.ArrayOfDoubleArrayBuilder


class DebugHttpServer(val baseAddress: String, val port: Int)
{
    private val _server = HttpServer.createSimpleServer("/", port)

    init {
        val config = _server.serverConfiguration

        config.addHttpHandler(
                InlineHttpHandler { req, res ->
                    val map = HeightMap(ArrayOfDoubleArrayBuilder.build(2, 2,
                            0.0, 0.0,
                            0.0, 0.0))
                    val json = Gson().toJson(map)
                    res?.writer?.write(json)
                    res?.setStatus(HttpStatus.OK_200)
                }, baseAddress + "map/1")
    }

    fun start(){
        _server.start()
    }

    fun stop(){
        _server.shutdown()
    }
}