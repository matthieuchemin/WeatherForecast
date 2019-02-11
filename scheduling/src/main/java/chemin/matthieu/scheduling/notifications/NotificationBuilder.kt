package chemin.matthieu.scheduling.notifications

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import chemin.matthieu.entities.ForecastOverview
import chemin.matthieu.scheduling.worker.NotificationWorker

class NotificationBuilder(private val context: Context, private val channelId: String) : NotificationWorker.NotificationBuilder {

    override fun build(overview: ForecastOverview) : Notification? =
            NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(android.R.drawable.ic_dialog_alert)
                    .setContentTitle(overview.locationName)
                    .setContentText("${overview.weather} ${overview.temperature}°C")
                    .build()
}
