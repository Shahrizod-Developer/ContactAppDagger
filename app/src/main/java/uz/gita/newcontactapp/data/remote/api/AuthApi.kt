package uz.gita.newcontactapp.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.newcontactapp.data.remote.request.AuthRequest
import uz.gita.newcontactapp.data.remote.response.AuthResponse
import uz.gita.newcontactapp.data.remote.response.BaseResponse

interface AuthApi {


    @POST("register")
    suspend fun register(
        @Body authRequest: AuthRequest
    ): Response<BaseResponse<AuthResponse>>

    @POST("unregister")
    suspend fun deleteAccount(
        @Body authRequest: AuthRequest
    ): Response<BaseResponse<AuthResponse>>

    @POST("login")
    suspend fun login(
        @Body authRequest: AuthRequest
    ): Response<BaseResponse<AuthResponse>>

    @POST("logout")
    suspend fun logout(
        @Body authRequest: AuthRequest
    ): Response<BaseResponse<AuthResponse>>

}