package uz.gita.newcontactapp.presentation.ui.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.newcontactapp.data.local.entity.ContactEntity
import uz.gita.newcontactapp.domain.usecase.ContactUseCase
import uz.gita.newcontactapp.presentation.ui.viewmodel.AddScreenViewModel
import javax.inject.Inject


@HiltViewModel
class AddScreenViewModelImpl @Inject constructor(private val contactUseCase: ContactUseCase) :
    AddScreenViewModel, ViewModel() {
    override val errorLiveData: MutableLiveData<String> = MutableLiveData()
    override val successLiveData: MutableLiveData<String> = MutableLiveData()
    override val openScreenLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun add(contactEntity: ContactEntity) {

            contactUseCase.addContact(contactEntity)
                .onEach { resultData ->
                resultData.onSuccess {
                    openScreenLiveData.value = Unit
                    successLiveData.value = "Kontact qo'shildi"
                }
            }.onEach { resultData ->
                resultData.onError {
                    errorLiveData.value = "Nimadir xato ketdi"
                }
            }.launchIn(viewModelScope)
        }
}