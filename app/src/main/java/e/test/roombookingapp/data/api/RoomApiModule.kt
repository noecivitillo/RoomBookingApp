package e.test.roombookingapp.data.api

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import e.test.roombookingapp.BuildConfig
import e.test.roombookingapp.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Noe on 8/11/2018.
 */

@Module
class RoomApiModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if(BuildConfig.DEBUG){
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(logging)
        }
        /**use this line to mock server...
         * Responses are handled in assets/
         */
        //builder.addInterceptor(MockServer(context))
        builder.connectTimeout(60*1000, TimeUnit.MILLISECONDS)
                .readTimeout(60*1000, TimeUnit.MILLISECONDS)

        return builder.build()
    }
    @Provides
    @Singleton
    fun provideRestAdapter(application: Application, okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        builder.client(okHttpClient)
                .baseUrl(application.getString(R.string.endpoint))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        return builder.build()
    }
    @Provides
    @Singleton
    fun provideRoomApiService(restAdapter: Retrofit): RoomApiService{
        return restAdapter.create(RoomApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}