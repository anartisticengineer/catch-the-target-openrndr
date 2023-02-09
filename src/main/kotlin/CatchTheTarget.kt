import dialog.GameOver
import game.TargetGame
import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.loadFont
import org.openrndr.extra.color.presets.DARK_GRAY
import org.openrndr.extra.color.presets.SLATE_GRAY
import org.openrndr.shape.Circle

fun main() = application {
    configure {
        width = 1080
        height = 960
        title = "Catch The Target"
    }

    program {
        var game = TargetGame(drawer.bounds)
        val gameOverScreen = GameOver(drawer.bounds)

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
            drawer.fontMap = loadFont("./data/fonts/SIMPLIFICA Typeface.ttf", 30.0, contentScale = 2.0)
            if (!game.gameOver) {
                game.runGame()
                //SCORE
                drawer.texts(game.hudText, game.hudPositions)
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
                drawer.texts(gameOverScreen.allTexts(game.score), gameOverScreen.allPositions)
            }
        }
    }
}
