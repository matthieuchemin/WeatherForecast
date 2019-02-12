package chemin.matthieu.domain

import org.apache.commons.lang3.time.DateUtils
import java.util.*
import java.util.concurrent.TimeUnit

class NextNotificationTime : UseCase<Long, Long>() {

    override fun perform(input: Long): Long {
        var date = Date(input)
        date = DateUtils.round(date, Calendar.HOUR)
        return date.time + TimeUnit.HOURS.toMillis(1)
        /*
        For debug purpose we choose to send notification every round hour.
        If we wanted to send it every morning at 9h00, we would to
        date = DateUtils.round(data, Calender.DAY)
        return dateTime + TimeUnit.HOURS.toMillis(9)
         */
    }
}