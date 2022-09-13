package uz.gita.newcontactapp.domain.repository.auth

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.newcontactapp.data.model.AuthData
import uz.gita.newcontactapp.data.model.AuthStateEnum
import uz.gita.newcontactapp.data.utils.ResultData

interface AuthRepository {

    fun createAccount(authData: AuthData): Flow<ResultData<Unit>>
    fun deleteAccount(): Flow<ResultData<Unit>>
    fun login(authData: AuthData): Flow<ResultData<Unit>>
    fun logout(): Flow<ResultData<Unit>>

}