package com.bt.mp3.data.di

import com.bt.mp3.data.BuildConfig
import com.bt.mp3.data.mock.network.HomeApiMock
import com.bt.mp3.data.mock.network.PlaylistApiMock
import com.bt.mp3.data.mock.network.SongApiMock
import com.bt.mp3.data.network.HomeApi
import com.bt.mp3.data.network.PlaylistApi
import com.bt.mp3.data.network.RetrofitBuilder
import com.bt.mp3.data.network.SongApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(retrofitBuilder: RetrofitBuilder): Retrofit = retrofitBuilder.build()

    @Singleton
    @Provides
    fun providePlaylistApi(retrofit: Retrofit): PlaylistApi =
        if (BuildConfig.DEBUG) {
            PlaylistApiMock()
        } else {
            retrofit.create(PlaylistApi::class.java)
        }

    @Singleton
    @Provides
    fun provideSongApi(retrofit: Retrofit): SongApi =
        if (BuildConfig.DEBUG) {
            SongApiMock()
        } else {
            retrofit.create(SongApi::class.java)
        }

    @Singleton
    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeApi =
        if (BuildConfig.DEBUG) {
            HomeApiMock()
        } else {
            retrofit.create(HomeApi::class.java)
        }
}
