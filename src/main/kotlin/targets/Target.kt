package targets

import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

abstract class Target(val boundaries: Rectangle) {
    val position = positionInPaddedBoundary()
    val radius = 20.0
    val distFromPlayer = { playerPosition: Vector2 ->
        this.position.distanceTo(playerPosition)
    }
    abstract val targetColor: ColorRGBa
    abstract val targetType: TargetType
    private fun positionInPaddedBoundary(): Vector2{
        val padding = 0.2
        val offset = boundaries.dimensions.times(padding)
        val randomPoint = Random.point(boundaries.times(1.0 - padding))
        return offset + randomPoint
    }
    abstract fun onHit()
}