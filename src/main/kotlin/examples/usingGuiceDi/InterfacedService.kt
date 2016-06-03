package examples.usingGuiceDi

class InterfacedService : IInterfacedService {
    override fun print(message: String?) = println("Interfaced Service: ${message}")
}