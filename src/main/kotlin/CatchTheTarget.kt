import dialog.GameOver
import game.TargetGame
import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.loadFont
import org.openrndr.extra.color.presets.DARK_GRAY
import org.openrndr.extra.color.presets.ORANGE_RED
import org.openrndr.extra.color.presets.SLATE_GRAY
import org.openrndr.extra.color.presets.WHITE_SMOKE
import org.openrndr.shape.Circle
import targets.TargetType

fun main() = application {
    configure {
        width = 1080
        height = 960
        title = "Catch The Target"
        hideCursor = true
    }

    program {
        var game = TargetGame(drawer.bounds)
        val gameOverScreen = GameOver(drawer.bounds)
        val fontDirectory = "./data/fonts/Oxygen-Regular.ttf"

        keyboard.keyDown.listen {
            game.player.changeDirection(it.key)
            if (game.gameOver) {
                if (it.name == "r"){
                    game = TargetGame(drawer.bounds)
                }
            }
        }
        extend {
            val targetContour = Circle(game.currentTarget.position, game.currentTarget.radius).contour

            drawer.clear(ColorRGBa.fromHex("#181818"))
            drawer.fill = ColorRGBa.WHITE_SMOKE
            drawer.stroke = ColorRGBa.SLATE_GRAY
            drawer.strokeWeight = 3.0
            drawer.fontMap = loadFont(fontDirectory, 30.0)

            if (!game.gameOver) {
                game.runGame()
                //SCORE
                drawer.texts(game.hudText, game.hudPositions)
                drawer.stroke = ColorRGBa.DARK_GRAY
                drawer.lineSegment(game.player.position, game.currentTarget.position)

                //PLAYER
                drawer.fill = null
                drawer.stroke = ColorRGBa.ORANGE_RED
                drawer.circle(Circle(game.player.position, game.player.radius))

                //TARGET
                drawer.stroke = game.currentTarget.targetColor
                when (game.currentTarget.targetType) {
                    TargetType.BAD -> {
                        drawer.contour(targetContour.sub(0.0, game.badTargetLifespan / 100.0))
                    }
                    TargetType.LIFE -> {
                        drawer.contour(targetContour.sub(0.0, game.lifeTargetLifespan / 100.0))
                    }
                    else -> {
                        drawer.contour(targetContour)
                    }
                }
            } else {
                drawer.texts(gameOverScreen.allTexts(game.score), gameOverScreen.allPositions)
            }
        }
    }
}
