package uz.gita.newcontactapp.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.newcontactapp.domain.repository.contact.ContactRepository
import uz.gita.newcontactapp.domain.repository.contact.impl.ContactRepositoryImpl
import uz.gita.newcontactapp.domain.usecase.AuthUseCase
import uz.gita.newcontactapp.domain.usecase.ContactUseCase
import uz.gita.newcontactapp.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.newcontactapp.domain.usecase.impl.ContactUseCaseImpl
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
interface AuthUseCaseModule {

    @[Binds]
    fun bind(impl: AuthUseCaseImpl): AuthUseCase

}