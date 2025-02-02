package ca.kilbourne.isaac.lysts.ui.components

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

private enum class TwoWayDraggableAnchors {
    Start,
    Center,
    End
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TwoWayAnchoredDraggableBox(
    offsetSize: Dp,
    modifier: Modifier = Modifier,
    leftContent: @Composable (modifier: Modifier) -> Unit = {},
    rightContent: @Composable (modifier: Modifier) -> Unit = {},
    content: @Composable (modifier: Modifier) -> Unit,
) {
    val density = LocalDensity.current
    val positionalThresholds: (totalDistance: Float) -> Float =
        { totalDistance -> totalDistance * 0.5f }
    val velocityThreshold: () -> Float = { with(density) { 100.dp.toPx() } }

    val state = remember {
        AnchoredDraggableState(
            initialValue = TwoWayDraggableAnchors.Center,
            positionalThreshold = positionalThresholds,
            velocityThreshold = velocityThreshold,
            snapAnimationSpec = tween<Float>(),
            decayAnimationSpec = exponentialDecay()
        ).apply {
            val newAnchors = with(density) {
                DraggableAnchors {
                    TwoWayDraggableAnchors.Start at offsetSize.toPx()
                    TwoWayDraggableAnchors.Center at 0.dp.toPx()
                    TwoWayDraggableAnchors.End at -offsetSize.toPx()
                }
            }
            updateAnchors(newAnchors)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .offset { IntOffset(state.requireOffset().roundToInt(), 0) }
    ) {
        leftContent(
            Modifier
                .align(Alignment.CenterStart)
                .offset(-offsetSize)
                .widthIn(max = offsetSize)
                .anchoredDraggable(state, Orientation.Horizontal)
        )

        content(
            Modifier
                .fillMaxWidth()
                .anchoredDraggable(state, Orientation.Horizontal)
        )

        rightContent(
            Modifier
                .align(Alignment.CenterEnd)
                .offset(offsetSize)
                .widthIn(max = offsetSize)
                .anchoredDraggable(state, Orientation.Horizontal)
        )
    }
}
