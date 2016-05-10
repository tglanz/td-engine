package framework

import java.util.concurrent.atomic.AtomicInteger

open class GameObject {
    private companion object IdGenerator {
        private var _nextId = AtomicInteger(0);
        fun generate(): Int = _nextId.incrementAndGet()
    }

    val id = IdGenerator.generate()
}