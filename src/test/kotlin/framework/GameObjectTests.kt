package framework

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class GameObjectTests {

    @Before
    fun setUp(){

    }

    @After
    fun tearDown(){

    }

    @Test
    fun ids_createObjects_idsIncremented(){
        val a = GameObject()
        val b = GameObject()
        val c = GameObject()

        assertEquals(1, a.id)
        assertEquals(2, b.id)
        assertEquals(3, c.id)
    }
}
