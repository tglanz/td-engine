package utils

import org.apache.commons.math3.exception.OutOfRangeException
import java.util.*

object ArrayOfDoubleArrayBuilder {
    fun build(rows: Int, cols: Int, vararg elements: Double): Array<DoubleArray> {

        val totalElementsCount = rows * cols
        if (totalElementsCount != elements.size){
            throw OutOfRangeException(elements.size, totalElementsCount, totalElementsCount)
        }

        val rowsList = ArrayList<DoubleArray>()

        for (row in 0..rows - 1){

            val colsList = ArrayList<Double>()

            val elementStartIndex = row * cols
            val elementEndIndex = elementStartIndex + cols - 1;

            for (col in elementStartIndex..elementEndIndex){
                colsList.add(elements[col])
            }

            rowsList.add(colsList.toDoubleArray())
        }

        return rowsList.toTypedArray()
    }
}