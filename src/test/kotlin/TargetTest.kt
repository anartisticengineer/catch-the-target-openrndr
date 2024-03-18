import game.TargetGame
import org.openrndr.shape.Rectangle
import targets.*
import targets.Target
import kotlin.test.Test
import kotlin.test.assertEquals

class TargetTest {
    private val boundary = Rectangle(0.0, 0.0, 100.0, 100.0)
    private val targetGame = TargetGame(boundary)
    private fun setAndRegister(target: Target = NormalTarget(boundary)) {
        targetGame.currentTarget = target
        targetGame.registerHit()
    }

    @Test
    fun testNormalTarget() {
        targetGame.registerHit()
        assert(targetGame.score > 0)
    }

    @Test
    fun testBonusTarget() {
        setAndRegister(BonusTarget(boundary))
        assert(targetGame.score > 0)
    }

    @Test
    fun testFreezeTarget() {
        setAndRegister(FreezeTarget(boundary))
        assert(targetGame.score > 0)
        assert(targetGame.player.speed < 5)
    }

    @Test
    fun testBadTarget() {
        setAndRegister(BadTarget(boundary))
        assertEquals(true, targetGame.gameOver)
    }

    @Test
    fun testLifeTarget() {
        setAndRegister(LifeTarget(boundary))
        assertEquals(4, targetGame.lives)
    }
}