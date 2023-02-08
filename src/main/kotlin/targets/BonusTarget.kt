package targets

import org.openrndr.color.ColorRGBa
import org.openrndr.extra.color.presets.GOLDENROD
import org.openrndr.shape.Rectangle

class BonusTarget(boundaries: Rectangle): Target(boundaries){
    override val targetColor: ColorRGBa = ColorRGBa.GOLDENROD
    override val targetType: TargetType = TargetType.BONUS
    override fun onHit() {
        println("Bonus Target")
    }
}