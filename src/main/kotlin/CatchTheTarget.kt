import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.shape.Circle
import player.Player

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        val player = Player(drawer.bounds.center, drawer.bounds)
        keyboard.keyDown.listen {
            player.changeDirection(it.key)
        }
        extend {
            drawer.fill = ColorRGBa.BLACK
            drawer.stroke = ColorRGBa.PINK
            player.update()
            drawer.circle(Circle(player.position, player.radius))
        }
    }
}
