@file:JvmName("TimeUtils")

package e.test.roombookingapp.utils

import android.util.Log
import e.test.roombookingapp.data.local.TimeDao
import e.test.roombookingapp.data.model.Time
import e.test.roombookingapp.data.model.TimeAvail
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Noe on 14/11/2018.
 */

//In case to save in database
fun initTimeData(timeDao: TimeDao) {

    val defaultTimeStart = "07:00"
    val defaultFinishTime = "19:00"
    val dateFormatStart = format(PATTERN_TIME).parse(defaultTimeStart)
    val dateFormatFinish = format(PATTERN_TIME).parse(defaultFinishTime)

    val cal = Calendar.getInstance()
    cal.setTime(dateFormatStart)
    //add start time to calendar
    while (cal.time.before(dateFormatFinish)) {
        cal.add(Calendar.MINUTE, 15)
        timeDao.insertTime(Time(format(PATTERN_TIME).format(java.sql.Time(cal.timeInMillis))))

    }
}
//Get strings times from room.avail
fun initTimeAvailObjFrom(listOfTimes: List<String>): List<TimeAvail> {
    val listAvail = ArrayList<TimeAvail>()
    for (t in listOfTimes) {
        val timeStart = t.substring(0, 5)
        val dateStart = format(PATTERN_TIME).parse(timeStart)
        val timeFinish = t.substring(8, 13)
        val dateFinish = format(PATTERN_TIME).parse(timeFinish)


        listAvail.add(TimeAvail(dateStart, dateFinish))
    }
    return listAvail
}

//Add avail times to calendar instance in order to obtain the range of times
fun getAllSingleTimesAvail(availTimes: List<TimeAvail>): ArrayList<java.sql.Time> {
    var finalList = ArrayList<java.sql.Time>(49)
    for (t in availTimes) {
        val cal = Calendar.getInstance()
        cal.setTime(t.timeStart)
        //add start time to calendar
        while (cal.time.before(t.timeFinish)) {
            cal.add(Calendar.MINUTE, 15)
            finalList.add(java.sql.Time(cal.timeInMillis))
        }
    }
    return finalList
}

fun format(pattern: String): SimpleDateFormat {
    return SimpleDateFormat(pattern, Locale.getDefault())
}

//Default times in a calendar instance in 15 min steps
fun initDefaultTimes(): List<java.sql.Time> {
    val defaultTimeStart = "07:00"
    val defaultFinishTime = "19:00"
    val dateFormatStart = format(PATTERN_TIME).parse(defaultTimeStart)
    val dateFormatFinish = format(PATTERN_TIME).parse(defaultFinishTime)

    val listTimes = java.util.ArrayList<java.sql.Time>(49)

    val cal = Calendar.getInstance()
    cal.setTime(dateFormatStart)
    //add start time to calendar
    while (cal.time.before(dateFormatFinish)) {
        cal.add(Calendar.MINUTE, 15)
        listTimes.add(java.sql.Time(cal.timeInMillis))
    }
    return listTimes
}

fun getTomorrowUnixTimeStamp(): String {
    val calendar = Calendar.getInstance()
    calendar.timeZone = TimeZone.getTimeZone("CET")
    calendar.add(Calendar.DAY_OF_YEAR, 1)

    val name = calendar.getTimeZone().getDisplayName()
    Log.i(" TimeUtils", "Current Time Zone: " + name)

    val unixTime = calendar.time.time / 1000

    return unixTime.toString()
}

fun getDateSelectedUnixTimeStamp(date: Long): String {
    val unixTime = date / 1000
    return unixTime.toString()
}

fun unixTimeStampToDateFormat(date: Long): String {
    val dat = Date(date)
    val sdf = format(PATTERN_YEAR_DAY_MONTH)
    sdf.timeZone = TimeZone.getTimeZone("CET")
    return sdf.format(dat)
}
fun getNextHour(): String{
    val cal = Calendar.getInstance()
    cal.add(Calendar.HOUR, 1)
    val nextHour = format(PATTERN_TIME)
    nextHour.timeZone = TimeZone.getTimeZone("CET")
    Log.i("TimeUtils ",  "Next hour is " +nextHour.format(cal.time.time))

    return nextHour.format(cal.time.time)
}






