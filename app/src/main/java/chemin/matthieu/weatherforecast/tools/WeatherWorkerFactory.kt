package chemin.matthieu.weatherforecast.tools

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import chemin.matthieu.weatherforecast.di.subcomponent.WorkerSubComponent
import javax.inject.Provider

class WeatherWorkerFactory(private val workerSubComponentBuilder: WorkerSubComponent.Builder) : WorkerFactory() {

    override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
    ) = workerSubComponentBuilder.workerParameters(workerParameters)
            .build().let {
                instantiateWorker(workerClassName, it.workerProviders())
            }


    private fun instantiateWorker(workerClassName: String, creators: Map<Class<out Worker>, Provider<Worker>>): Worker {
        val workerClass = Class.forName(workerClassName).asSubclass(Worker::class.java)

        val creator = creators[workerClass]
                ?: creators[creators.keys.find { workerClass.isAssignableFrom(it) }]
                ?: throw IllegalArgumentException("Unknown model class: $workerClass")

        return try {
            @Suppress("UNCHECKED_CAST")
            creator.get()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}