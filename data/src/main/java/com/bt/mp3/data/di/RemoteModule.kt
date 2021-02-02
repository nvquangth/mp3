package com.bt.mp3.data.di

import android.content.Context
import com.bt.mp3.data.BuildConfig
import com.bt.mp3.data.encryption.Encryption
import com.bt.mp3.data.interceptor.SignatureInterceptor
import com.bt.mp3.data.mock.network.PlaylistApiMock
import com.bt.mp3.data.mock.network.SectionApiMock
import com.bt.mp3.data.mock.network.SongApiMock
import com.bt.mp3.data.network.HomeRemoteDataSource
import com.bt.mp3.data.network.HomeRemoteDataSourceImpl
import com.bt.mp3.data.network.retrofit.HomeRetrofitApi
import com.bt.mp3.data.network.retrofit.PlaylistApi
import com.bt.mp3.data.network.retrofit.RetrofitBuilder
import com.bt.mp3.data.network.retrofit.SectionApi
import com.bt.mp3.data.network.retrofit.SongApi
import com.bt.mp3.data.network.volley.HomeVolleyApi
import com.bt.mp3.data.network.volley.HomeVolleyApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(retrofitBuilder: RetrofitBuilder, signatureInterceptor: SignatureInterceptor): Retrofit = retrofitBuilder
        .addInterceptors(signatureInterceptor)
        .build()

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
    fun provideHomeApi(retrofit: Retrofit): HomeRetrofitApi =
        if (BuildConfig.DEBUG) {
//            HomeApiMock(context)
            retrofit.create(HomeRetrofitApi::class.java)
        } else {
            retrofit.create(HomeRetrofitApi::class.java)
        }

    @Singleton
    @Provides
    fun provideSectionApi(retrofit: Retrofit): SectionApi =
        if (BuildConfig.DEBUG) {
            SectionApiMock()
        } else {
            retrofit.create(SectionApi::class.java)
        }

    @Singleton
    @Provides
    fun provideHomeVolleyApi(@ApplicationContext context: Context, encryption: Encryption): HomeVolleyApi {
        return HomeVolleyApiImpl(context, encryption)
    }

    @Singleton
    @Provides
    fun provideHomeRemoteDataSource(homeRetrofitApi: HomeRetrofitApi, homeVolleyApi: HomeVolleyApi): HomeRemoteDataSource {
        return HomeRemoteDataSourceImpl(homeRetrofitApi, homeVolleyApi)
    }
}
