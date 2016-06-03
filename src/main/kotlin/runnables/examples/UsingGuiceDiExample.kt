package runnables.examples

import com.google.inject.Guice
import examples.usingGuiceDi.UsingGuiceDiApplication

object UsingGuiceDiExample {
    @JvmStatic fun main(args: Array<String>) {
        UsingGuiceDiApplication.create().run()
    }
}