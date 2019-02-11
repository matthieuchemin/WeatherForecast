package chemin.matthieu.domain

import org.apache.commons.lang3.time.DateUtils
import java.util.*

class NextNotificationTime : UseCase<Long, Long>() {

    override fun perform(input: Long): Long {
        var date = Date(input)
        date = DateUtils.round(date, Calendar.HOUR)
        return date.time
    }
}