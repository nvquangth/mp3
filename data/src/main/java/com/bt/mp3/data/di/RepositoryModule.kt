package com.bt.mp3.data.di

import com.bt.mp3.data.repositoryimpl.HomeRepositoryImpl
import com.bt.mp3.data.repositoryimpl.PlaylistRepositoryImpl
import com.bt.mp3.data.repositoryimpl.SongRepositoryImpl
import com.bt.mp3.domain.repository.HomeRepository
import com.bt.mp3.domain.repository.PlaylistRepository
import com.bt.mp3.domain.repository.SongRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindPlaylistRepository(playlistRepositoryImpl: PlaylistRepositoryImpl): PlaylistRepository

    @Singleton
    @Binds
    abstract fun bindSongRepository(songRepositoryImpl: SongRepositoryImpl): SongRepository

    @Singleton
    @Binds
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}
