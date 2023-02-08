package player

import org.openrndr.KEY_ARROW_DOWN
import org.openrndr.KEY_ARROW_LEFT
import org.openrndr.KEY_ARROW_RIGHT
import org.openrndr.KEY_ARROW_UP
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

class Player(initialPosition: Vector2, private val boundaries: Rectangle) {
    val radius = 20.0
    var position = initialPosition
    var speed = 2.0
    private var dir = Vector2.ZERO
    val inBounds
        get() = boundaries.contains(position)

    fun changeDirection(key: Int){
        dir = when(key){
            KEY_ARROW_RIGHT -> Vector2(1.0, 0.0)
            KEY_ARROW_LEFT -> Vector2(-1.0, 0.0)
            KEY_ARROW_DOWN -> Vector2(0.0, 1.0)
            KEY_ARROW_UP -> Vector2(0.0, -1.0)
            else -> {
                dir
            }
        }
    }

    fun update(){
        position += (dir.times(speed))
    }
}