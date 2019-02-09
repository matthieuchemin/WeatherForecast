package chemin.matthieu.domain

import chemin.matthieu.commontesttools.mock
import chemin.matthieu.entities.Forecast
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.TimeUnit

class TestReadForecastForPosition {

    private lateinit var forecast: Forecast
    private lateinit var forecastRepository: ReadForecastForPosition.ForecastRepository
    private lateinit var taskScheduler: ReadForecastForPosition.TaskScheduler
    private lateinit var readForecastForPosition: ReadForecastForPosition

    @Before
    fun setUp() {
        forecast = mock()

        // mock forecast repository, make sure it returns something
        forecastRepository = mock()
        Mockito.`when`(forecastRepository.forecastForLocation(SUCCESS_FORECAST_LOCATION_ID)).thenReturn(Success(forecast))
        Mockito.`when`(forecastRepository.forecastForLocation(FAILED_FORECAST_LOCATION_ID)).thenReturn(Failure())

        // mock task scheduler to verify how it has been called
        taskScheduler = mock()

        // create object to test
        readForecastForPosition = ReadForecastForPosition(forecastRepository, taskScheduler)
    }

    @Test
    fun test_repositorySuccess() {
        val result = readForecastForPosition.perform(SUCCESS_FORECAST_LOCATION_ID)

        // assert we have the correct result
        Assert.assertEquals(forecast, result)
        Mockito.verify(taskScheduler, Mockito.times(1)).scheduleNextExecution(SUCCESS_RESCHEDULING_TIME)
    }

    @Test
    fun test_repositoryFail() {
        val result = readForecastForPosition.perform(FAILED_FORECAST_LOCATION_ID)

        // assert we have correct behavior
        Assert.assertNull(result)
        Mockito.verify(taskScheduler, Mockito.times(1)).scheduleNextExecution(FAILED_RESCHEDULING_TIME)
    }

    private companion object {
        const val SUCCESS_FORECAST_LOCATION_ID = 105L
        val SUCCESS_RESCHEDULING_TIME = TimeUnit.HOURS.toMillis(6)
        const val FAILED_FORECAST_LOCATION_ID = 25L
        val FAILED_RESCHEDULING_TIME = TimeUnit.MINUTES.toMillis(15)
    }

}