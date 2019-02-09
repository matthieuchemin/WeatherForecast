package chemin.matthieu.domain

abstract class UseCase<InputT, OutputT> {

    abstract suspend fun perform(input: InputT): OutputT
}