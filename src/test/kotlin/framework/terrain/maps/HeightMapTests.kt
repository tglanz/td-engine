package framework.terrain.maps

import org.junit.Assert.assertEquals

import org.junit.After
import org.junit.Before
import org.junit.Test
import utils.ArrayOfDoubleArrayBuilder

class HeightMapTests {

    val _doubleThreshold = 0.001

    val _onesHeight = ArrayOfDoubleArrayBuilder.build(3, 3,
            1.0, 1.0, 1.0,
            1.0, 1.0, 1.0,
            1.0, 1.0, 1.0)

    val _tileSloped = ArrayOfDoubleArrayBuilder.build(2, 2,
            0.0, 1.0,
            0.0, 1.0)

    @Before
    fun setUp(){ }

    @After
    fun tearDown(){ }

    @Test
    fun createHeightMap_setOnesHeightNoScale_correctPositionHeightValues(){
        val map = HeightMap(_onesHeight)
        for (row in 0..2){
            for (col in 0..2){
                assert(1.0 == map.getHeightAtEntry(row, col))
            }
        }
    }

    @Test
    fun createHeightMap_setOnesHeightNoScale_correctCoordHeightValues(){
        val map = HeightMap(_onesHeight)
        assertEquals(1.0, map.getHeightAtCoord(0.5, 0.5), _doubleThreshold)
    }

    @Test
    fun createHeightMap_setOnesHeightWithScale_correctPositionHeightValues(){
        val map = HeightMap(_onesHeight)
        map.setScale(1.0, 1.0, 3.14)
        for (row in 0..2){
            for (col in 0..2){
                assertEquals(3.14, map.getHeightAtEntry(row, col), _doubleThreshold)
            }
        }
    }

    @Test
    fun createHeightMap_getHeightAtCoordOfTileSlopedNoScale_correctCoordHeightValues(){
        val map = HeightMap(_tileSloped)
        assertEquals(0.5, map.getHeightAtCoord(0.5, 0.5), _doubleThreshold)
    }

    @Test
    fun createHeightMap_getHeightAtCoordOfTileSlopedWithScale_correctCoordHeightValues(){
        val map = HeightMap(_tileSloped)
        map.setScale(2.0, 2.0, 1.0)
        assertEquals(0.25, map.getHeightAtCoord(0.5, 0.5), _doubleThreshold)
    }
}