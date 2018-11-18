package e.test.roombookingapp.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import e.test.roombookingapp.data.api.RoomApiService
import e.test.roombookingapp.data.api.manager.RoomManager
import e.test.roombookingapp.data.local.AppDatabase
import e.test.roombookingapp.utils.NetworkHandler

/**
 * Created by Noe on 8/11/2018.
 */
@Module
class AppModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun providesAppDatabase(context: Context): AppDatabase {
        //context.deleteDatabase("roomBooking-db")
        return Room.databaseBuilder(context, AppDatabase::class.java, "roomBooking-db").allowMainThreadQueries().build()
    }
    @Provides
    fun providesNetworkHandler(context: Context): NetworkHandler{
        return NetworkHandler(context)
    }

    @Provides
    fun providesRoomDao(database: AppDatabase)= database.roomDao()

    @Provides
    fun providesTimeDao(database: AppDatabase)= database.timeDao()
    /**
    @Provides
    fun providesEventDao(database: AppDatabase)= database.eventDao()

    @Provides
    fun providesParticipantDao(database: AppDatabase)= database.participantDao()
    */

    @Provides
    fun providesRoomManager(roomApiService: RoomApiService): RoomManager{
        return RoomManager(roomApiService)
    }
}