package uz.gita.newcontactapp.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.newcontactapp.data.model.AuthData
import uz.gita.newcontactapp.data.remote.request.AuthRequest
import uz.gita.newcontactapp.data.utils.MessageData

interface RegisterScreenViewModel {

    val loadingLiveData: LiveData<Boolean>
    val messageLiveData: LiveData<MessageData>
    val successLiveData: LiveData<Unit>

    fun createAccount(authData: AuthData)
}