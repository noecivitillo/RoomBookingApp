package e.test.roombookingapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import e.test.roombookingapp.data.model.Room
import e.test.roombookingapp.data.model.Time


/**
 * Created by Noe on 8/11/2018.
 */

// Event::class, Participant::class
@Database(entities = arrayOf(Room::class, Time::class), version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
  abstract fun roomDao(): RoomDao
  abstract fun timeDao(): TimeDao
  /**
  abstract fun eventDao(): EventDao
  abstract fun participantDao(): ParticipantDao
  */

}