package uz.gita.newcontactapp.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newcontactapp.domain.repository.auth.AuthRepository
import uz.gita.newcontactapp.domain.repository.auth.impl.AuthRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {

    @[Binds Singleton]
    fun bind(impl: AuthRepositoryImpl): AuthRepository
}