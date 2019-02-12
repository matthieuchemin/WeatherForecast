package chemin.matthieu.presentation

import androidx.lifecycle.ViewModel
import chemin.matthieu.commontesttools.mock
import chemin.matthieu.presentation.viewmodel.FavoredLocationViewModel
import chemin.matthieu.presentation.viewmodel.ForecastViewModel
import chemin.matthieu.presentation.viewmodel.ScopedViewModel
import chemin.matthieu.presentation.viewmodel.ViewModelFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Provider

class TestViewModelFactory {

    private lateinit var viewModel: ForecastViewModel
    private lateinit var clazz: Class<ForecastViewModel>
    private lateinit var provider: Provider<ViewModel>
    private lateinit var creators: Map<Class<out ViewModel>, Provider<ViewModel>>
    private lateinit var viewModelFactory: ViewModelFactory

    @Before
    fun setUp() {
        viewModel = mock()
        clazz = ForecastViewModel::class.java
        provider = mock()
        Mockito.`when`(provider.get()).thenReturn(viewModel)
        creators = mapOf(Pair(clazz, provider))
        viewModelFactory = ViewModelFactory(creators)
    }

    @Test
    fun test_existingProvider_exact() {
        viewModelFactory.create(ForecastViewModel::class.java)
    }

    @Test
    fun test_existingProvider_assignable() {
        viewModelFactory.create(ScopedViewModel::class.java)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_nonExistingProvider() {
        viewModelFactory.create(FavoredLocationViewModel::class.java)
    }
}