package dialog

import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

class GameOver(boundaries: Rectangle) {
    val gameOverText = "Game Over :("
    val restartText = "Press 'r' to restart."
    val results = {score: Int -> "Final Score: $score"}
    val allPositions = listOf<Vector2>()
}