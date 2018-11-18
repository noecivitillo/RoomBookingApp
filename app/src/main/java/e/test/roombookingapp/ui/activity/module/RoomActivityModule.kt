package e.test.roombookingapp.ui.activity.module

import dagger.Module
import dagger.Provides
import e.test.roombookingapp.data.api.manager.RoomManager
import e.test.roombookingapp.ui.activity.RoomActivity
import e.test.roombookingapp.ui.activity.presenter.RoomsPresenter
import e.test.roombookingapp.utils.NetworkHandler

/**
 * Created by Noe on 13/11/2018.
 */
@Module
class RoomActivityModule {
    @Provides
    fun providesRoomActivity(roomActivity: RoomActivity): RoomActivity {
        return roomActivity
    }
    @Provides
    fun providesRoomActivityPresenter(roomManager: RoomManager, networkHandler: NetworkHandler): RoomsPresenter{
        return RoomsPresenter(roomManager, networkHandler)
    }

}