package targets

import org.openrndr.color.ColorRGBa
import org.openrndr.shape.Rectangle

class NormalTarget(boundaries: Rectangle) : Target(boundaries) {
    override val targetColor: ColorRGBa = ColorRGBa.BLACK
    override val targetType: TargetType = TargetType.NORMAL
    override fun onHit() {
        println("Normal Target Hit")
    }
}