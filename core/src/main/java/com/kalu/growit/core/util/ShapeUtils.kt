package com.kalu.growit.core.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


enum class CornerType {
    CUT,
    ROUNDED,
    NONE
}

data class ShapeCorner(
    val type: CornerType = CornerType.NONE,
    val size: Dp = 0.dp
)

data class ShapeDecoration(
    val topStart: ShapeCorner = ShapeCorner(),
    val topEnd: ShapeCorner = ShapeCorner(),
    val bottomEnd: ShapeCorner = ShapeCorner(),
    val bottomStart: ShapeCorner = ShapeCorner()
)


class BottomRoundedArcShape(
    private val decoration: ShapeDecoration
) : Shape {


    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val topStartSize = decoration.topStart.size.value * density.density
        val topEndSize = decoration.topEnd.size.value * density.density
        val bottomEndSize = decoration.bottomEnd.size.value * density.density
        val bottomStartSize = decoration.bottomStart.size.value * density.density

        var path = Path().apply {
            reset()
            // go from (0,0) to (width, 0)
            lineTo(size.width, 0f)

            // go from (width, 0) to (width, height)
            lineTo(size.width, size.height)

            // Draw an arch from (width, height) to (0, height)
            // starting from 0 degree to 180 degree
            arcTo(
                rect =
                Rect(
                    Offset(0f, 0f),
                    Size(size.width, size.height)
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            // go from (0, height) to (0, 0)
            lineTo(0f, 0f)
            close()

        }

        return Outline.Generic(
            path = path
        )
    }
}
