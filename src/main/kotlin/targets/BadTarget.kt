package targets

import org.openrndr.color.ColorRGBa
import org.openrndr.extra.color.presets.DEEP_PINK
import org.openrndr.shape.Rectangle

class BadTarget(boundaries: Rectangle): Target(boundaries) {
    override val targetColor: ColorRGBa = ColorRGBa.DEEP_PINK
    override fun onHit() {
        println("Bad Target")
    }
}