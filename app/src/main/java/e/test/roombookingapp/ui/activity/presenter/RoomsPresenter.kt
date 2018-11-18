package e.test.roombookingapp.ui.activity.presenter

import e.test.roombookingapp.data.api.manager.RoomManager
import e.test.roombookingapp.data.model.Room
import e.test.roombookingapp.utils.*
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * Created by Noe on 13/11/2018.
 */
class RoomsPresenter(var roomManager: RoomManager, var networkHandler: NetworkHandler) {
    var presentation: RoomsPresentation ? = null
    var listRooms: List<Room> = emptyList()

    fun onCreate(roomsPresentation: RoomsPresentation) {
        presentation = roomsPresentation
        listRooms= emptyList()

        loadRooms(DEFAULT_DATE)
    }
    fun onDestroy() {
        presentation = null
    }
    fun loadRooms(date: String){
        presentation?.showLoading(true)
        when(networkHandler.isConnected) {
            true -> {
                roomManager.listRoomsFromApi(date).subscribe(object : Observer<List<Room>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<Room>) {
                        presentation?.showLoading(false)
                        presentation?.showRooms(t)
                        listRooms = t
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                        presentation?.showLoading(false)
                        presentation?.showMessage(ERROR)
                    }
                })
            }
            false, null -> {
                presentation?.showLoading(false)
                presentation?.showMessage(NO_INTERNET)
            }
        }
    }
    fun onClickToday(){
        loadRooms("today")
        SELECTED_DATE = "today"
        presentation?.showCheckedButtons(BUTTON_TODAY)
        presentation?.showTagDateSelected(false, null)
    }
    fun onClickTomorrow(){
        val unixTime = getTomorrowUnixTimeStamp()
        loadRooms(unixTime)
        SELECTED_DATE= unixTime
        presentation?.showCheckedButtons(BUTTON_TOMORROW)
        presentation?.showTagDateSelected(false, null)
    }
    fun onClickPickADate(){
        presentation?.initPickerDialog()
        presentation?.showCheckedButtons(BUTTON_PICk_DATE)
    }
    fun onSelectedDate(date: Long){
        val dateTimeStamp = getDateSelectedUnixTimeStamp(date)
        val string = unixTimeStampToDateFormat(date)
        loadRooms(dateTimeStamp)
        SELECTED_DATE = dateTimeStamp
        presentation?.showTagDateSelected(true, string)
    }
    fun onOpenRoom(room: Room){
        presentation?.showBookActivity(room)
    }
    fun onSearchQuery(param: String){
        val newlist = createNewListWithResults(param)
        listRooms = newlist
        presentation?.showSearchResults(newlist)
    }
    private fun createNewListWithResults(param: String): List<Room>{
        val newList = ArrayList<Room>()
        for(room in listRooms) {
            if (room.name.contains(param))
                newList.add(room)
        }
        return newList
        }

}