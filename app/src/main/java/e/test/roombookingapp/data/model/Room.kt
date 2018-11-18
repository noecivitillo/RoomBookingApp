package e.test.roombookingapp.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Noe on 8/11/2018.
 */

@Parcelize
@Entity(tableName = "rooms")

class Room(@ColumnInfo(name = "id")
           @PrimaryKey(autoGenerate = true)
           var id: Int = 0,
           @ColumnInfo(name = "name")
           @SerializedName("name")
           var name: String = "",
           @ColumnInfo(name = "location")
           @SerializedName("location")
           @Ignore
           var location: String = "",
           @Ignore
           @SerializedName("equipment")
           var equipment: List<String>? = null,
           @ColumnInfo(name = "size")
           @SerializedName("size")
           var size: String = "",
           @ColumnInfo(name = "capacity")
           @SerializedName("capacity")
           var capacity: Int = 0,
           @Ignore
           @SerializedName("avail")
           var avail: List<String>? = null,
           @Ignore
           @SerializedName("images")
           var images: List<String>? = null
          ) : Parcelable