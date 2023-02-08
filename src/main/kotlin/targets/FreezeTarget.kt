package targets

import org.openrndr.color.ColorRGBa
import org.openrndr.extra.color.presets.DEEP_SKY_BLUE
import org.openrndr.shape.Rectangle

class FreezeTarget(boundaries: Rectangle): Target(boundaries) {
    override val targetColor: ColorRGBa = ColorRGBa.DEEP_SKY_BLUE
    override val targetType: TargetType = TargetType.FREEZE
    override fun onHit() {
        println("Freeze Target")
    }
}