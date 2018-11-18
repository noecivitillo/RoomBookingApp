package e.test.roombookingapp.data.local

import android.arch.persistence.room.*
import e.test.roombookingapp.data.model.Room

/**
 * Created by Noe on 8/11/2018.
 */
@Dao
interface RoomDao {

    @Query("select * from rooms ")
    fun getAllRooms(): List<Room>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoom(room: Room)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateRoom(room: Room)

    @Delete
    fun delete(room: Room)
}