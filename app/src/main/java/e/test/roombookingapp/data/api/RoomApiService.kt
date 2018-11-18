package e.test.roombookingapp.data.api

import e.test.roombookingapp.data.api.request_response.RoomRequest
import e.test.roombookingapp.data.model.Room
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by Noe on 8/11/2018.
 */
interface RoomApiService {

    @POST("getrooms")
    fun getAllRooms(@Body roomRequest: RoomRequest): Observable<List<Room>>

}