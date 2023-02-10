package targets

import org.openrndr.color.ColorRGBa
import org.openrndr.extra.color.presets.LIME_GREEN
import org.openrndr.shape.Rectangle

class LifeTarget(boundaries: Rectangle): Target(boundaries) {
    override val targetColor: ColorRGBa = ColorRGBa.LIME_GREEN
    override val targetType: TargetType = TargetType.LIFE
    override fun onHit() {
        println("Extra Life")
    }
}