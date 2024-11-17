// Original Github Gist: https://gist.github.com/leonardoaramaki/153b27eb5325f878ad4bb7ffe540c2ef

package ca.kilbourne.isaac.lysts.ui.components

import android.os.SystemClock
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed


/**
 * Wraps an [onClick] lambda with another one that supports debouncing. The default debouncing time
 * is 1000ms.
 *
 * @return debounced onClick
 */
@Composable
inline fun debounced(debounceTime: Long = 1000L, crossinline onClick: () -> Unit): () -> Unit {
    val lastTimeClicked = remember { mutableLongStateOf(0L) }
    val onClickLambda: () -> Unit = {
        val now = SystemClock.uptimeMillis()

        if (now - lastTimeClicked.longValue > debounceTime) {
            onClick()
        }

        lastTimeClicked.longValue = now
    }

    return onClickLambda
}

/**
 * The same as [Modifier.clickable] with support to debouncing.
 */
fun Modifier.debouncedClickable(
    debounceTime: Long = 1000L,
    onClick: () -> Unit
): Modifier {
    return this.composed {
        val clickable = debounced(debounceTime = debounceTime, onClick = { onClick() })
        this.clickable { clickable() }
    }
}