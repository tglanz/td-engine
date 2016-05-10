package framework.terrain.maps

import com.sun.javafx.geom.Matrix3f
import com.sun.javafx.geom.Vec2f
import com.sun.javafx.geom.Vec3f
import framework.terrain.TerrainObject
import org.apache.commons.math3.geometry.euclidean.threed.Plane
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D
import java.awt.List
import java.util.*

class HeightMap() : TerrainObject(){

    private var _matrixRows: Array<Array<Float>> = arrayOf<Array<Float>>()
    private val _scale = Vec3f()

    fun setRows(rows: Array<Array<Float>>){
        var rowsList = ArrayList<Array<Float>>();

        rows.forEach { floats ->
            rowsList.add(floats.copyOf())
        }

        _matrixRows = rowsList.toTypedArray()
    }

    fun setScale(x: Float, y: Float, z: Float){
        _scale.x = x
        _scale.y = y
        _scale.z = z
    }

    fun setScale(scale: Vec3f) = setScale(scale.x, scale.y, scale.z)

    fun getHeightAtPosition(row: Int, column: Int) =
            _matrixRows[row][column] * _scale.z

    fun getHeightAtCoord(x: Float, y: Float): Float {

        // TODO: This is the main idea...
        // Should be changed though to be precise

        val normalizedX = x / _scale.x
        val normalizedY = y / _scale.y

        val row = Math.round(normalizedY)
        val col = Math.round(normalizedX)

        val z1 = _matrixRows[row][col]
        val z2 = _matrixRows[row + 1][col]
        val z3 = _matrixRows[row][col + 1]

        val plane = Plane(
                Vector3D(row.toDouble(), col.toDouble(), z1.toDouble()),
                Vector3D((row + 1).toDouble(), col.toDouble(), z2.toDouble()),
                Vector3D(row.toDouble(), (col + 1).toDouble(), z3.toDouble()),
                0.0)

        return plane.getPointAt(Vector2D(row.toDouble(), col.toDouble()), 0.0).z.toFloat()
    }
}