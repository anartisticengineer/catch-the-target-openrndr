package game

import dialog.HUD
import org.openrndr.shape.Rectangle
import player.Player
import targets.*
import targets.Target
import kotlin.random.Random

class TargetGame(boundaries: Rectangle) {
    private val boundaryRectangle = boundaries
    private val hud = HUD(boundaries)
    private val speedIncrease = 0.2
    private val speedDecrease = 0.9
    private var badTargetLifespan = 0
    private var lives = 3
    val player = Player(boundaries.center, boundaries)
    var currentTarget: Target = NormalTarget(boundaries)
    var score = 0
    val hudText
        get() = hud.allText(lives, score)
    val hudPositions
        get() = hud.allPositions
    val gameOver
        get() = lives < 0

    private fun getNextTarget(boundaries: Rectangle): Target {
        return when(Random.nextInt(1,8)){
            1 -> BonusTarget(boundaries)
            2 -> FreezeTarget(boundaries)
            3 -> BadTarget(boundaries)
            else -> {
                NormalTarget(boundaries)
            }
        }
    }

    fun runGame(){
        player.update()
        //BAD Target
        if (currentTarget.targetType == TargetType.BAD){
            badTargetLifespan -= 1
            if (badTargetLifespan <= 0) {
                badTargetLifespan = 0
                currentTarget = getNextTarget(boundaryRectangle)
            }
        }
        //HIT
        if (currentTarget.distFromPlayer(player.position) < 15.0){
            when(currentTarget.targetType){
                TargetType.BONUS -> {
                    score += 5
                    player.speed += speedIncrease
                }
                TargetType.FREEZE -> {
                    score += 1
                    player.speed *= speedDecrease
                }
                TargetType.BAD -> {
                    lives = -1
                }
                else -> {
                    score += 1
                    player.speed += speedIncrease
                }
            }
            currentTarget = getNextTarget(boundaryRectangle)
            badTargetLifespan = if (currentTarget.targetType == TargetType.BAD) 100 else 0
        }
        //OUT OF BOUNDS
        if (!player.inBounds) {
            lives -= 1
            player.position = boundaryRectangle.center
        }
    }
}