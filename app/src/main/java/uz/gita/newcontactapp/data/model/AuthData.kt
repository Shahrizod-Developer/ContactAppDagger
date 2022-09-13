package uz.gita.newcontactapp.data.model

import uz.gita.newcontactapp.data.remote.request.AuthRequest

data class AuthData(
    val name: String,
    val password: String
) {
    fun toRequest() = AuthRequest(name, password)
}