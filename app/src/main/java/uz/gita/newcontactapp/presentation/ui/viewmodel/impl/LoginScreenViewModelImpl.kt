package uz.gita.newcontactapp.presentation.ui.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.newcontactapp.data.model.AuthData
import uz.gita.newcontactapp.data.utils.MessageData
import uz.gita.newcontactapp.domain.usecase.AuthUseCase
import uz.gita.newcontactapp.presentation.ui.viewmodel.LoginScreenViewModel
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor(private val authUseCase: AuthUseCase) :
    LoginScreenViewModel, ViewModel() {

    override val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val messageLiveData: MutableLiveData<MessageData> = MutableLiveData()
    override val successLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun login(authData: AuthData) {
        loadingLiveData.value = true

        if (authData.name.isNotEmpty() && authData.password.isNotEmpty()) {

            if (authData.password.length > 5) {
                authUseCase.login(authData)
                    .onEach { loadingLiveData.postValue(false) }
                    .onEach { resultData -> resultData.onSuccess { successLiveData.postValue(Unit) } }
                    .onEach { resultData -> resultData.onMessage { messageLiveData.postValue(it) } }
                    .launchIn(viewModelScope)
            } else {
                messageLiveData.postValue(MessageData.messageText("Parol kam"))
            }
        } else {
            messageLiveData.postValue(MessageData.messageText("Maydonlar bo'sh"))
        }
    }
}