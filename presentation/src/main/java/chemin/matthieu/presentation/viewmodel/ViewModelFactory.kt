package chemin.matthieu.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class ViewModelFactory(private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
                ?: creators[creators.keys.find { modelClass.isAssignableFrom(it) }]
                ?: throw IllegalArgumentException("Unknown model class: $modelClass")

        return try {
            @Suppress("UNCHECKED_CAST")
            creator.get() as T
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}