package utils

import org.apache.commons.math3.exception.OutOfRangeException
import org.junit.Assert.*
import org.junit.Test

class ArrayOfDoubleArrayBuilderTests {

    @Test
    fun arrayOfDoubleArrayBuilder_buildDoubleArray_correctValues(){
        val matrix = ArrayOfDoubleArrayBuilder.build(2, 3,
                0.0, 0.2, 0.6,
                0.7, 0.9, 1.1)

        assertEquals(0.0, matrix[0][0], 0.0)
        assertEquals(0.2, matrix[0][1], 0.0)
        assertEquals(0.6, matrix[0][2], 0.0)
        assertEquals(0.7, matrix[1][0], 0.0)
        assertEquals(0.9, matrix[1][1], 0.0)
        assertEquals(1.1, matrix[1][2], 0.0)
    }

    @Test(expected = OutOfRangeException::class)
    fun arrayOfDoubleArrayBuilder_buildDoubleArrayOverflowDimensions_outOfRangeException(){
        val matrix = ArrayOfDoubleArrayBuilder.build(1, 1,
                0.0, 1.0)
    }

    @Test(expected = OutOfRangeException::class)
    fun arrayOfDoubleArrayBuilder_buildDoubleArrayUnderflowDimensions_outOfRangeException(){
        val matrix = ArrayOfDoubleArrayBuilder.build(2, 2,
                0.0)
    }
}