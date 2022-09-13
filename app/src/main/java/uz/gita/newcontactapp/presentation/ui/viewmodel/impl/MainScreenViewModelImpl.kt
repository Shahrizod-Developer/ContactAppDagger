package uz.gita.newcontactapp.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import uz.gita.newcontactapp.data.local.entity.ContactEntity
import uz.gita.newcontactapp.data.utils.MessageData
import uz.gita.newcontactapp.domain.usecase.AuthUseCase
import uz.gita.newcontactapp.domain.usecase.ContactUseCase
import uz.gita.newcontactapp.presentation.ui.viewmodel.MainScreenViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(
    private val contactUseCase: ContactUseCase,
    private val authUseCase: AuthUseCase
) :
    MainScreenViewModel, ViewModel() {

    override val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val messageLiveData: MutableLiveData<MessageData> = MutableLiveData()
    override val successLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val openAddScreenLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val openUpdateScreenLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val onClickLogoutLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val onClickDeleteAccountLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val listLiveData: MutableLiveData<List<ContactEntity>> = MutableLiveData()

    init {
        getAllContact()
    }

    override fun getAllContact() {

        contactUseCase.getAllContacts().onEach {
            it.onSuccess {
                listLiveData.value = it
            }
        }.launchIn(viewModelScope)
    }

    override fun sync() {

        contactUseCase.sync()
            .onEach { loadingLiveData.postValue(false) }
            .onEach { resultData -> resultData.onSuccess { successLiveData.postValue(Unit) } }
            .onEach { resultData -> resultData.onMessage { messageLiveData.postValue(it) } }
            .launchIn(viewModelScope)

    }

    override fun delete(contactEntity: ContactEntity) {

        contactUseCase.delete(contactEntity)
            .onEach { loadingLiveData.postValue(false) }
            .onEach { resultData -> resultData.onSuccess { successLiveData.postValue(Unit) } }
            .onEach { resultData -> resultData.onMessage { messageLiveData.postValue(it) } }
            .launchIn(viewModelScope)
    }

    override fun deleteAccount() {
        authUseCase.deleteAccount()
            .onEach { loadingLiveData.postValue(false) }
            .onEach { resultData ->
                resultData.onSuccess {
                    onClickDeleteAccountLiveData.postValue(Unit)
                    successLiveData.postValue(Unit)
                }
            }
            .onEach { resultData ->
                resultData.onMessage { messageLiveData.postValue(it) }
            }.launchIn(viewModelScope)
    }

    override fun logout() {
        authUseCase.logout()
            .onEach { loadingLiveData.postValue(false) }
            .onEach { resultData ->
                resultData.onSuccess {
                    onClickLogoutLiveData.postValue(Unit)
                    successLiveData.postValue(Unit)
                }
            }
            .onEach { resultData -> resultData.onMessage { messageLiveData.postValue(it) } }
            .launchIn(viewModelScope)
    }
}