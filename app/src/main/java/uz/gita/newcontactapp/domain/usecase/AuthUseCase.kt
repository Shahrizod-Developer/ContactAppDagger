package uz.gita.newcontactapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.newcontactapp.data.model.AuthData
import uz.gita.newcontactapp.data.model.AuthStateEnum
import uz.gita.newcontactapp.data.utils.ResultData

interface AuthUseCase {

    fun createAccount(authData: AuthData): Flow<ResultData<Unit>>

    fun deleteAccount(): Flow<ResultData<Unit>>

    fun login(authData: AuthData): Flow<ResultData<Unit>>

    fun logout(): Flow<ResultData<Unit>>

}