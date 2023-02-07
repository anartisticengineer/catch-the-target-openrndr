import org.openrndr.*
import org.openrndr.color.ColorRGBa
import org.openrndr.shape.Circle
import org.openrndr.shape.Rectangle
import player.Player
import targets.BadTarget
import targets.BonusTarget
import targets.FreezeTarget
import targets.NormalTarget
import targets.Target
import kotlin.random.Random

fun nextTarget(randomIndex: Int, boundaries: Rectangle): Target{
    return when(Random.nextInt(1,8)){
        1 -> BonusTarget(boundaries)
        2 -> FreezeTarget(boundaries)
        3 -> BadTarget(boundaries)
        else -> {
            NormalTarget(boundaries)
        }
    }
}

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        val player = Player(drawer.bounds.center, drawer.bounds)
        var currentTarget = nextTarget(Random.nextInt(1,8), drawer.bounds)

        keyboard.keyDown.listen {
            player.changeDirection(it.key)
        }
        extend {
            drawer.clear(ColorRGBa.BLACK)
            drawer.fill = null
            drawer.stroke = ColorRGBa.PINK
            player.update()
            drawer.circle(Circle(player.position, player.radius))

            //TARGET
            drawer.stroke = currentTarget.targetColor
            drawer.circle(currentTarget.position, currentTarget.radius)

            if (currentTarget.distFromPlayer(player.position) < 10.0){
                currentTarget.onHit()
                currentTarget = nextTarget(Random.nextInt(1,8), drawer.bounds)
            }
        }
    }
}
