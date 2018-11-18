package e.test.roombookingapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import e.test.roombookingapp.ui.activity.BookActivity
import e.test.roombookingapp.ui.activity.RoomActivity
import e.test.roombookingapp.ui.activity.module.BookActivityModule
import e.test.roombookingapp.ui.activity.module.RoomActivityModule

/**
 * Created by Noe on 8/11/2018.
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = arrayOf(RoomActivityModule::class))
    abstract fun bindRoomActivity(): RoomActivity
    @ContributesAndroidInjector(modules = arrayOf(BookActivityModule::class))
    abstract fun bindBookActivity(): BookActivity

}