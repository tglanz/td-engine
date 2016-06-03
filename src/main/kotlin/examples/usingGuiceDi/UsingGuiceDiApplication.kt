package examples.usingGuiceDi

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Inject

class UsingGuiceDiApplication @Inject constructor (
        private val _justService: JustService,
        private val _interfacedService: InterfacedService
){
    fun run() {
        _justService.print("Hi")
        _interfacedService.print("Hi")
    }

    companion object {

        class UsingGuicDiApplicationModule : AbstractModule {

            constructor(){

            }

            override fun configure() {
                bind(IInterfacedService::class.java).to(InterfacedService::class.java)
            }
        }

        fun create() : UsingGuiceDiApplication =
                Guice.createInjector(UsingGuicDiApplicationModule()).getInstance(UsingGuiceDiApplication::class.java)
    }
}