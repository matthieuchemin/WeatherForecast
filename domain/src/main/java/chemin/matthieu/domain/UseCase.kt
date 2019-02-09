package chemin.matthieu.domain

abstract class UseCase<InputT, OutputT> {

    abstract fun perform(input: InputT): OutputT
}