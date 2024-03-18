import org.junit.jupiter.api.BeforeEach
import org.openrndr.KEY_ARROW_DOWN
import org.openrndr.KEY_ARROW_LEFT
import org.openrndr.KEY_ARROW_RIGHT
import org.openrndr.KEY_ARROW_UP
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle
import player.Player
import kotlin.test.Test
import kotlin.test.assertEquals

class PlayerTest {
    private val boundary = Rectangle(0.0, 0.0, 100.0,100.0)
    private lateinit var player: Player
    @BeforeEach
    fun setPlayer() {
        player = Player(Vector2.ZERO, boundary)
    }

    @Test
    fun testInBounds() {
        val inBoundsPlayer = Player(Vector2.ZERO, boundary)
        assertEquals(true, inBoundsPlayer.inBounds)
    }

    @Test
    fun testOutOfBounds() {
        val outOfBoundsPlayer: Player = Player(Vector2(200.0, 200.0), boundary)
        assertEquals(false, outOfBoundsPlayer.inBounds)
    }

    @Test
    fun testMoveLeft() {
        val xPosition = -player.speed
        player.changeDirection(KEY_ARROW_LEFT)
        player.update()
        assertEquals(xPosition, player.position.x)
    }

    @Test
    fun testMoveRight() {
        val xPosition = player.speed
        player.changeDirection(KEY_ARROW_RIGHT)
        player.update()
        assertEquals(xPosition, player.position.x)
    }

    @Test
    fun testMoveUp() {
        val yPosition = -player.speed
        player.changeDirection(KEY_ARROW_UP)
        player.update()
        assertEquals(yPosition, player.position.y)
    }

    @Test
    fun testMoveDown() {
        val yPosition = player.speed
        player.changeDirection(KEY_ARROW_DOWN)
        player.update()
        assertEquals(yPosition, player.position.y)
    }
}