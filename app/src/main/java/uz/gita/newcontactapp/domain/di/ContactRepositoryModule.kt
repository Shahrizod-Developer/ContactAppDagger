package uz.gita.newcontactapp.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newcontactapp.domain.repository.auth.AuthRepository
import uz.gita.newcontactapp.domain.repository.auth.impl.AuthRepositoryImpl
import uz.gita.newcontactapp.domain.repository.contact.ContactRepository
import uz.gita.newcontactapp.domain.repository.contact.impl.ContactRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface ContactRepositoryModule {

    @[Binds Singleton]
    fun bind(impl: ContactRepositoryImpl): ContactRepository
}