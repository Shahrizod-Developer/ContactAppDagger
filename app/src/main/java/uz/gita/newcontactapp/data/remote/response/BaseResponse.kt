package uz.gita.newcontactapp.data.remote.response

data class BaseResponse<T>(
    val massage: String,
    val data: T
)