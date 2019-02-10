package chemin.matthieu.presentation.viewmodel

import androidx.lifecycle.ViewModel
import chemin.matthieu.commontools.e
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

private const val TAG = "ScopedViewModel"

abstract class ScopedViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    private val coroutineExceptionHandler =
            CoroutineExceptionHandler { _, throwable -> handleThrowableFromCoroutine(throwable) }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + coroutineExceptionHandler

    open fun handleThrowableFromCoroutine(throwable: Throwable) {
        Timber.e(TAG, "Unexpected exception bubble up from coroutine !!")
        Timber.e(TAG, throwable)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}