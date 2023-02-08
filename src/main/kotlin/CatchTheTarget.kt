import game.TargetGame
import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.color.presets.DARK_GRAY
import org.openrndr.extra.color.presets.SLATE_GRAY
import org.openrndr.math.Vector2
import org.openrndr.shape.Circle

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        var game = TargetGame(drawer.bounds)

        keyboard.keyDown.listen {
            game.player.changeDirection(it.key)
            if (game.gameOver) {
                if (it.name == "r"){
                    game = TargetGame(drawer.bounds)
                }
            }
        }
        extend {
            drawer.clear(ColorRGBa.DARK_GRAY)
            drawer.fill = ColorRGBa.BLACK
            drawer.stroke = ColorRGBa.BLACK
            drawer.strokeWeight = 3.0
            if (!game.gameOver) {
                game.runGame()
                //SCORE
                drawer.texts(
                    listOf("Score: ${game.score}", "Lives: ${game.lives}"),
                    listOf(Vector2(20.0, 20.0), Vector2(20.0, height.toDouble() - 20.0))
                )
                drawer.stroke = ColorRGBa.SLATE_GRAY
                drawer.lineSegment(game.player.position, game.currentTarget.position)

                //PLAYER
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK
                drawer.circle(Circle(game.player.position, game.player.radius))

                //TARGET
                drawer.stroke = game.currentTarget.targetColor
                drawer.circle(game.currentTarget.position, game.currentTarget.radius)
            } else {
                drawer.text("GAME OVER :(\nPress 'r' to restart.", drawer.bounds.center)
            }
        }
    }
}
