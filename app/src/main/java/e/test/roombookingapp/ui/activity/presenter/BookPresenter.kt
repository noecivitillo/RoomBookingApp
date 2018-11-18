package e.test.roombookingapp.ui.activity.presenter

import e.test.roombookingapp.data.model.Room

/**
 * Created by Noe on 16/11/2018.
 */
class BookPresenter {
    var presentation: BookPresentation ? = null

    fun onCreate(bookPresentation: BookPresentation) {
        presentation = bookPresentation


    }
    fun onDestroy() {
        presentation = null
    }
    fun initDataRoom(room: Room){
        presentation?.showDataRoom(room)

    }

}