package e.test.roombookingapp.data.api.manager

import e.test.roombookingapp.data.api.RoomApiService
import e.test.roombookingapp.data.api.request_response.RoomRequest
import e.test.roombookingapp.data.model.Room
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Noe on 13/11/2018.
 */
class RoomManager(var roomApiService: RoomApiService) {

    fun listRoomsFromApi(date: String): Observable<List<Room>> {

        var roomRequest = RoomRequest()
        roomRequest.date = date

        return roomApiService.getAllRooms(roomRequest)
                .doOnNext {
                    //store all rooms in database
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}