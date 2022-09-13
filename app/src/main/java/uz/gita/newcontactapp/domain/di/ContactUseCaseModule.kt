package uz.gita.newcontactapp.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.gita.newcontactapp.domain.usecase.AuthUseCase
import uz.gita.newcontactapp.domain.usecase.ContactUseCase
import uz.gita.newcontactapp.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.newcontactapp.domain.usecase.impl.ContactUseCaseImpl
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
interface ContactUseCaseModule {

    @[Binds]
    fun bind(impl: ContactUseCaseImpl): ContactUseCase
}