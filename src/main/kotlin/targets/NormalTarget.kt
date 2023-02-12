package targets

import org.openrndr.color.ColorRGBa
import org.openrndr.extra.color.presets.ANTIQUE_WHITE
import org.openrndr.shape.Rectangle

class NormalTarget(boundaries: Rectangle) : Target(boundaries) {
    override val targetColor: ColorRGBa = ColorRGBa.ANTIQUE_WHITE
    override val targetType: TargetType = TargetType.NORMAL
    override fun onHit() {
        println("Normal Target Hit")
    }
}