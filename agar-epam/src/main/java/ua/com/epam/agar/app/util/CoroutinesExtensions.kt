package ua.com.epam.agar.app.util

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Coroutines extensions
 *
 **/

fun CoroutineScope.launchSilent(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    this.launch(context, start, block)
}

/**
 * Same as [GlobalScope.launch] but returns [Unit]
 */
fun launchSilent(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    GlobalScope.launchSilent(context, start, block)
}

/**
 * Workaround b/c of exceptions that [SendChannel.offer] throws in case of closed channel..
 * @return false if any exception catched on offer function
 */
fun <E> SendChannel<E>.offerCatching(element: E): Boolean {
    return runCatching { offer(element) }.getOrDefault(false)
}

fun <T> Flow<T>.collectWhenStarted(
    viewScope: LifecycleCoroutineScope,
    action: suspend (value: T) -> Unit
) {
    viewScope.launchWhenStarted {
        collect {
            action(it)
        }
    }
}

fun <T> Flow<T>.collectWhenResumed(
    viewScope: LifecycleCoroutineScope,
    action: suspend (value: T) -> Unit
) {
    viewScope.launchWhenResumed {
        collect {
            action(it)
        }
    }
}

fun <T> Flow<T>.collectWhenCreated(
    viewScope: LifecycleCoroutineScope,
    action: suspend (value: T) -> Unit
) {
    viewScope.launchWhenCreated {
        collect {
            action(it)
        }
    }
}
