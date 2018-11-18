package e.test.roombookingapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import e.test.roombookingapp.RoomApplication
import e.test.roombookingapp.data.api.RoomApiModule
import javax.inject.Singleton

/**
 * Created by Noe on 8/11/2018.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RoomApiModule::class, AndroidInjectionModule::class, AndroidSupportInjectionModule::class, ActivityBuilder::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application (application: Application) : Builder
        fun build(): AppComponent
    }
    fun inject(app: RoomApplication)

}