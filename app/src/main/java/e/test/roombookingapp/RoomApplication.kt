package e.test.roombookingapp

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import e.test.roombookingapp.di.AppComponent
import e.test.roombookingapp.di.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by Noe on 8/11/2018.
 */
class RoomApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: AppComponent
        private set


    companion object {
        lateinit var app: RoomApplication
            private set

    }

    override fun onCreate() {
        super.onCreate()
        app = this
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()
        appComponent.inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }


}