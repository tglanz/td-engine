package framework.terrain.maps

import framework.terrain.TerrainObject
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D
import org.apache.commons.math3.geometry.euclidean.threed.Plane
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D
import org.apache.commons.math3.linear.MatrixUtils
import java.util.*

class HeightMap(rows: Array<DoubleArray>) : TerrainObject(){

    private val _matrix = MatrixUtils.createRealMatrix(rows)
    private var _scale = Vector3D(1.0, 1.0, 1.0)

    fun setScale(x: Double, y: Double, z: Double){
        _scale = Vector3D(x, y, z)
    }

    fun getHeightAtEntry(row: Int, column: Int) =
            _matrix.getEntry(row, column) * _scale.z

    fun getHeightAtCoord(x: Double, y: Double): Double {
        val normalizedX = x / _scale.x
        val normalizedY = y / _scale.y

        val row = Math.round(normalizedY - 0.5).toInt()
        val col = Math.round(normalizedX - 0.5).toInt()

        val row1 = row + 1
        val col1 = col + 1

        val z1 = _matrix.getEntry(row, col)
        val z2 = _matrix.getEntry(row1, col)
        val z3 = _matrix.getEntry(row, col1)

        val a = Vector3D(col * _scale.x, row * _scale.y, z1)
        val b = Vector3D(col * _scale.x, row1 * _scale.y, z2)
        val c = Vector3D(col1 * _scale.x, row * _scale.y, z3)

        val ab = b.subtract(a)
        val ac = c.subtract(a)
        val cross = ab.crossProduct(ac)
        val d = -1.0 * (cross.x * a.x + cross.y * a.y + cross.z * a.z)

        val z = -1.0 / cross.z * (cross.x * x + cross.y * y + d)

        return z
    }
}