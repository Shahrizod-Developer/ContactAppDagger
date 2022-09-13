package uz.gita.newcontactapp.domain.repository.auth.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newcontactapp.R
import uz.gita.newcontactapp.data.local.database.AppDatabase
import uz.gita.newcontactapp.data.local.shp.MySharedPreference
import uz.gita.newcontactapp.data.model.AuthData
import uz.gita.newcontactapp.data.model.AuthStateEnum
import uz.gita.newcontactapp.data.remote.api.AuthApi
import uz.gita.newcontactapp.data.remote.api.ContactApi
import uz.gita.newcontactapp.data.remote.request.AuthRequest
import uz.gita.newcontactapp.data.utils.MessageData
import uz.gita.newcontactapp.data.utils.ResultData
import uz.gita.newcontactapp.data.utils.hasConnection
import uz.gita.newcontactapp.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
) : AuthRepository {

    private val dao = AppDatabase.getInstance().contactDao()
    private val sharedPreferences = MySharedPreference.getInstance()

    override fun createAccount(authData: AuthData): Flow<ResultData<Unit>> = flow {

        val response = authApi.register(authData.toRequest())

        if (hasConnection()) {
            try {

                when (response.code()) {

                    in 200..299 -> {

                        sharedPreferences.token = response.body()?.data?.token.toString()
                        sharedPreferences.name = authData.name
                        sharedPreferences.password = authData.password
                        emit(ResultData.success(Unit))
                    }
                    else -> {
                        emit(
                            ResultData.message(
                                MessageData.messageText(
                                    response.errorBody()?.string().toString()
                                )
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(
                    ResultData.message(
                        MessageData.messageText(
                            e.toString()
                        )
                    )
                )
            }
        } else {
            emit(
                ResultData.message(
                    MessageData.messageText(
                        "Internet yo'q"
                    )
                )
            )
        }
    }.catch { emit(ResultData.message(MessageData.messageResource(R.string.connection_error))) }
        .flowOn(Dispatchers.IO)

    override fun deleteAccount(): Flow<ResultData<Unit>> = flow {
        val response =
            authApi.deleteAccount(AuthRequest(sharedPreferences.name, sharedPreferences.password))

        if (hasConnection()) {
            try {

                when (response.code()) {

                    in 200..299 -> {

                        sharedPreferences.token = ""
                        dao.delete()
                        emit(ResultData.success(Unit))
                    }
                    else -> {
                        emit(
                            ResultData.message(
                                MessageData.messageText(
                                    response.errorBody()?.string().toString()
                                )
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(
                    ResultData.message(
                        MessageData.messageText(
                            e.toString()
                        )
                    )
                )
            }
        } else {
            emit(
                ResultData.message(
                    MessageData.messageText(
                        "Internet yo'q"
                    )
                )
            )
        }
    }.catch { emit(ResultData.message(MessageData.messageResource(R.string.connection_error))) }
        .flowOn(Dispatchers.IO)

    override fun login(authData: AuthData): Flow<ResultData<Unit>> = flow {
        val response = authApi.login(authData.toRequest())

        if (hasConnection()) {
            try {

                when (response.code()) {

                    in 200..299 -> {

                        sharedPreferences.token = response.body()?.data?.token.toString()
                        sharedPreferences.name = authData.name
                        sharedPreferences.password = authData.password
                        emit(ResultData.success(Unit))
                    }
                    else -> {
                        emit(
                            ResultData.message(
                                MessageData.messageText(
                                    response.errorBody()?.string().toString()
                                )
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(
                    ResultData.message(
                        MessageData.messageText(
                            e.toString()
                        )
                    )
                )
            }
        } else {
            emit(
                ResultData.message(
                    MessageData.messageText(
                        "Internet yo'q"
                    )
                )
            )
        }
    }.catch { emit(ResultData.message(MessageData.messageResource(R.string.connection_error))) }
        .flowOn(Dispatchers.IO)

    override fun logout(): Flow<ResultData<Unit>> = flow {
        val response =
            authApi.logout(AuthRequest(sharedPreferences.name, sharedPreferences.password))

        if (hasConnection()) {
            try {

                when (response.code()) {

                    in 200..299 -> {

                        sharedPreferences.token = ""
                        emit(ResultData.success(Unit))
                    }
                    else -> {
                        emit(
                            ResultData.message(
                                MessageData.messageText(
                                    response.errorBody()?.string().toString()
                                )
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(
                    ResultData.message(
                        MessageData.messageText(
                            e.toString()
                        )
                    )
                )
            }
        } else {
            emit(
                ResultData.message(
                    MessageData.messageText(
                        "Internet yo'q"
                    )
                )
            )
        }
    }.catch { emit(ResultData.message(MessageData.messageResource(R.string.connection_error))) }
        .flowOn(Dispatchers.IO)


}
