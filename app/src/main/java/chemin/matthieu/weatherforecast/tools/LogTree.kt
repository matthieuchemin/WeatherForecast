package chemin.matthieu.weatherforecast.tools

import android.util.Log
import chemin.matthieu.commontools.exhaustive
import timber.log.Tree

private const val TAG = "LogTree"

class LogTree : Tree() {

    override fun performLog(priority: Int, tag: String?, throwable: Throwable?, message: String?) {
        val currentTag = tag ?: TAG.also {
            Log.e(TAG, "log something with null tag !!")
        }
        if (throwable == null && message == null) {
            Log.e(TAG, "try to log with no message, nor throwable")
        }
        if (throwable != null) {
            Log.e(currentTag, throwable.localizedMessage)
            throwable.printStackTrace()
        }
        if (message != null) {
            when (priority) {
                Log.DEBUG -> Log.d(currentTag, message)
                Log.VERBOSE -> Log.v(currentTag, message)
                Log.INFO -> Log.i(currentTag, message)
                Log.WARN -> Log.w(currentTag, message)
                Log.ERROR -> Log.e(currentTag, message)
                Log.ASSERT -> Log.wtf(currentTag, message)
                else -> {
                    Log.e(TAG, "unknown log priority $priority")
                    Log.wtf(currentTag, message)
                }
            }.exhaustive
        }
    }
}