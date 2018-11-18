package e.test.roombookingapp.data.api.request_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Noe on 13/11/2018.
 */
class RoomResponse {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("location")
    @Expose
    var location: String? = null
    @SerializedName("equipment")
    @Expose
    var equipment: List<String>? = null
    @SerializedName("size")
    @Expose
    var size: String? = null
    @SerializedName("capacity")
    @Expose
    var capacity: Int? = null
    @SerializedName("avail")
    @Expose
    var avail: List<String>? = null
    @SerializedName("images")
    @Expose
    var images: List<String>? = null

}