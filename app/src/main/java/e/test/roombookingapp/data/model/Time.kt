package e.test.roombookingapp.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * Created by Noe on 14/11/2018.
 */

@Entity(tableName = "times")
data class Time(
        @ColumnInfo(name = "time_default")var time: String){
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int= 0
}