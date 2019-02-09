package chemin.matthieu.domain

sealed class Result<T>

class Success<T>(val data: T) : Result<T>()

class Failure<T> : Result<T>()