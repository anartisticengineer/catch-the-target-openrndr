package targets

import org.openrndr.color.ColorRGBa
import org.openrndr.extra.color.presets.GHOST_WHITE
import org.openrndr.shape.Rectangle

class NormalTarget(boundaries: Rectangle) : Target(boundaries) {
    override val targetColor: ColorRGBa = ColorRGBa.GHOST_WHITE
    override fun onHit() {
        println("Normal Target Hit")
    }
}