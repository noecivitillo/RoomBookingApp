package e.test.roombookingapp.ui.activity.presenter

import e.test.roombookingapp.data.model.Room

/**
 * Created by Noe on 13/11/2018.
 */
interface RoomsPresentation {
    fun showLoading(visibility : Boolean)
    fun showRooms(list: List<Room>)
    fun showMessage(message: Int)
    fun initPickerDialog()
    fun showTagDateSelected(visibility: Boolean, date:String?)
    fun showCheckedButtons(button : Int)
    fun showBookActivity(room: Room)
    fun showSearchResults(list: List<Room>)
}