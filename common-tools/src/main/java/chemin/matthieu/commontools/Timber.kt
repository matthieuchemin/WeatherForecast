package chemin.matthieu.commontools

import timber.log.Timber

const val VERBOSE = 2
const val DEBUG = 3
const val INFO = 4
const val WARNING = 5
const val ERROR = 6
const val ASSERT = 7

fun Timber.v(tag: String, message: String) {
    Timber.log(priority = Timber.VERBOSE, tag = tag, throwable = null, message = message)
}

fun Timber.v(tag: String, throwable: Throwable) {
    Timber.log(priority = Timber.VERBOSE, tag = tag, throwable = throwable, message = null)
}

fun Timber.d(tag: String, message: String) {
    Timber.log(priority = Timber.DEBUG, tag = tag, throwable = null, message = message)
}

fun Timber.d(tag: String, throwable: Throwable) {
    Timber.log(priority = Timber.DEBUG, tag = tag, throwable = throwable, message = null)
}

fun Timber.i(tag: String, message: String) {
    Timber.log(priority = Timber.INFO, tag = tag, throwable = null, message = message)
}

fun Timber.i(tag: String, throwable: Throwable) {
    Timber.log(priority = Timber.INFO, tag = tag, throwable = throwable, message = null)
}

fun Timber.w(tag: String, message: String) {
    Timber.log(priority = Timber.WARNING, tag = tag, throwable = null, message = message)
}

fun Timber.w(tag: String, throwable: Throwable) {
    Timber.log(priority = Timber.WARNING, tag = tag, throwable = throwable, message = null)
}

fun Timber.e(tag: String, message: String) {
    Timber.log(priority = Timber.ERROR, tag = tag, throwable = null, message = message)
}

fun Timber.e(tag: String, throwable: Throwable) {
    Timber.log(priority = Timber.ERROR, tag = tag, throwable = throwable, message = null)
}

