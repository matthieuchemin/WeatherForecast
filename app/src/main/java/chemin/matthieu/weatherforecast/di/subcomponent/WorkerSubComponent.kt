package chemin.matthieu.weatherforecast.di.subcomponent

import androidx.work.Worker
import androidx.work.WorkerParameters
import chemin.matthieu.weatherforecast.di.module.WorkerBindingModule
import chemin.matthieu.weatherforecast.di.module.WorkerInstantitationModule
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Provider

@Subcomponent(
        modules = [
            WorkerBindingModule::class,
            WorkerInstantitationModule::class
        ]
)
interface WorkerSubComponent {

    fun workerProviders(): @JvmSuppressWildcards Map<Class<out Worker>, Provider<Worker>>

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun workerParameters(param: WorkerParameters): Builder

        fun build(): WorkerSubComponent
    }
}