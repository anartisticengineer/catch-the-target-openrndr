package dialog

import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

class HUD(boundaries: Rectangle) {
    private val padding = 30.0
    val allText = {lives: Int, score: Int -> listOf("Lives: $lives", "Score: $score") }
    val allPositions = listOf(Vector2(padding, padding), Vector2(padding, boundaries.height - padding))
}