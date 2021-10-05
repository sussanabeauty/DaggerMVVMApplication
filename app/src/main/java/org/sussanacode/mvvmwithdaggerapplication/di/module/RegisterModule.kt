package org.sussanacode.mvvmwithdaggerapplication.di.module


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sussanacode.mvvmwithdaggerapplication.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RegisterModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : ApiService{

        return Retrofit.Builder()
            .baseUrl("https://grocery-second-app.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    }


    @Singleton
    @Provides
    fun providesLoggingInterceptor() : HttpLoggingInterceptor {

        return  HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY }

    }


}