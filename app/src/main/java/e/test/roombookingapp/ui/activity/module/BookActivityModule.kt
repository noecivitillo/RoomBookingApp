package e.test.roombookingapp.ui.activity.module

import dagger.Module
import dagger.Provides
import e.test.roombookingapp.ui.activity.presenter.BookPresenter

/**
 * Created by Noe on 16/11/2018.
 */
@Module
class BookActivityModule {
    @Provides
    fun providesBookPresenter(): BookPresenter{
        return BookPresenter()
    }

}