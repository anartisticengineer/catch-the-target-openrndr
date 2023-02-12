package dialog

import org.openrndr.shape.Rectangle

class GameOver(boundaries: Rectangle) {
    private val gameOverText = "Game Over :("
    private val restartText = "Press 'r' to restart."
    val allTexts = {score: Int -> listOf(gameOverText, restartText, "Final Score: $score")}
    val allPositions = listOf(0.5, 0.6, 0.7).map {
        boundaries.center.copy(y = boundaries.height * it)
    }
}