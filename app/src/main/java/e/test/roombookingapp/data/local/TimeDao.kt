package e.test.roombookingapp.data.local

import android.arch.persistence.room.*
import e.test.roombookingapp.data.model.Time

/**
 * Created by Noe on 14/11/2018.
 */
@Dao
interface TimeDao{

    @Query("select * from times")
    fun getAllTimes(): List<Time>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTime(time: Time)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTime(time: Time)

    @Delete
    fun delete(time: Time)
}