package game

import dialog.HUD
import org.openrndr.shape.Rectangle
import player.Player
import targets.*
import targets.Target
import kotlin.random.Random

class TargetGame(val boundaries: Rectangle) {
    private val hud = HUD(boundaries)
    private val speedIncrease = 0.2
    private val speedDecrease = 0.9
    private var timeBonus = 100.0
    val player = Player(boundaries.center, boundaries)
    var currentTarget: Target = NormalTarget(boundaries)
    var badTargetLifespan = 0
    var lifeTargetLifespan = 0
    var lives = 3
    var score = 0
    val hudText
        get() = hud.allText(lives, score)
    val hudPositions
        get() = hud.allPositions
    val gameOver
        get() = lives < 0

    private fun getNextTarget(): Target {
        timeBonus = 100.0
        return when(if (score % 50 < 40) 0 else Random.nextInt(1, 5)){
            1 -> BonusTarget(boundaries)
            2 -> FreezeTarget(boundaries)
            3 -> BadTarget(boundaries)
            4 -> LifeTarget(boundaries)
            else -> {
                NormalTarget(boundaries)
            }
        }
    }

    private fun difficultyFactor(): Int {
        return (boundaries.center.distanceTo(currentTarget.position) / 100).toInt()
    }

    fun registerHit() {
        when(currentTarget.targetType){
            TargetType.BONUS -> {
                score += (5 * (difficultyFactor() + timeBonus.toInt()))
                player.speed += speedIncrease
            }
            TargetType.FREEZE -> {
                score += difficultyFactor() + timeBonus.toInt()
                player.speed *= speedDecrease
            }
            TargetType.BAD -> {
                lives = -1
            }
            TargetType.LIFE -> {
                lives += 1
                score += difficultyFactor() + timeBonus.toInt()
            }
            else -> {
                score += difficultyFactor() + timeBonus.toInt()
                player.speed += speedIncrease
            }
        }
        currentTarget = getNextTarget()
        badTargetLifespan = if (currentTarget.targetType == TargetType.BAD) 100 else 0
        lifeTargetLifespan = if (currentTarget.targetType == TargetType.LIFE) 100 else 0
    }

    fun runGame(){
        player.update()
        timeBonus *= 0.99
        //BAD Target
        if (currentTarget.targetType == TargetType.BAD){
            badTargetLifespan -= 1
            if (badTargetLifespan <= 0) {
                badTargetLifespan = 0
                currentTarget = getNextTarget()
            }
        }
        //LIFE Target
        if (currentTarget.targetType == TargetType.LIFE) {
            lifeTargetLifespan -= 1
            if (lifeTargetLifespan <= 0) {
                lifeTargetLifespan = 0
                currentTarget = getNextTarget()
            }
        }
        //HIT
        if (currentTarget.distFromPlayer(player.position) < 20.0){
            registerHit()
        }
        //OUT OF BOUNDS
        if (!player.inBounds) {
            lives -= 1
            player.position = boundaries.center
        }
    }
}