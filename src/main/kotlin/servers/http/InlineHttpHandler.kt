package servers.http

import org.glassfish.grizzly.http.server.HttpHandler
import org.glassfish.grizzly.http.server.Request
import org.glassfish.grizzly.http.server.Response
import org.glassfish.grizzly.http.util.HttpStatus

class InlineHttpHandler(val handler: (request: Request?, response: Response?) -> Unit) : HttpHandler(){

    private var _acceptanceMethods = arrayOf<String>("GET")

    override fun service(request: Request?, response: Response?) {
        println("[${request?.method}] ${request?.requestURI.toString()}")

        if (_acceptanceMethods.contains(request?.method.toString())) {
            handler(request, response)
        } else {
            response?.setStatus(HttpStatus.NOT_ACCEPTABLE_406)
            val errorMessage = "[${request?.method.toString()}] Method not supported"
            println(errorMessage)
            response?.writer?.write(errorMessage)
        }
    }

    fun acceptMethods(vararg methods: String): InlineHttpHandler {
        _acceptanceMethods = methods.map { x -> x.toUpperCase() }.distinct().toTypedArray()
        return this
    }
}