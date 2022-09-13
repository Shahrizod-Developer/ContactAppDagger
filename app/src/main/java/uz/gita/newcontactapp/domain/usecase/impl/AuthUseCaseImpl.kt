package uz.gita.newcontactapp.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newcontactapp.data.model.AuthData
import uz.gita.newcontactapp.data.model.AuthStateEnum
import uz.gita.newcontactapp.data.utils.ResultData
import uz.gita.newcontactapp.domain.repository.auth.AuthRepository
import uz.gita.newcontactapp.domain.usecase.AuthUseCase
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    AuthUseCase {

    override fun createAccount(authData: AuthData): Flow<ResultData<Unit>> =
        authRepository.createAccount(authData)

    override fun deleteAccount(): Flow<ResultData<Unit>> =
        authRepository.deleteAccount()

    override fun login(authData: AuthData): Flow<ResultData<Unit>> =
        authRepository.login(authData)

    override fun logout(): Flow<ResultData<Unit>> =
        authRepository.logout()

}