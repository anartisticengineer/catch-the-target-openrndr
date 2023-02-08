package targets

import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

abstract class Target(boundaries: Rectangle) {
    val position = Random.point(boundaries)
    val radius = 20.0
    val distFromPlayer = { playerPosition: Vector2 ->
        this.position.distanceTo(playerPosition)
    }
    abstract val targetColor: ColorRGBa
    abstract val targetType: TargetType
    abstract fun onHit()
}