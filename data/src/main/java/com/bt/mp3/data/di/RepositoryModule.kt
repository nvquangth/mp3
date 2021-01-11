package com.bt.mp3.data.di

import com.bt.mp3.data.repositoryimpl.PlaylistRepositoryImpl
import com.bt.mp3.domain.repository.PlaylistRepository
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
}
