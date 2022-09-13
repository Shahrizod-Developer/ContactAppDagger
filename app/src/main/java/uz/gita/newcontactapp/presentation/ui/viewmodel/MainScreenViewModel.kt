package uz.gita.newcontactapp.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.newcontactapp.data.local.entity.ContactEntity
import uz.gita.newcontactapp.data.utils.MessageData

interface MainScreenViewModel {

    val loadingLiveData: LiveData<Boolean>
    val messageLiveData: LiveData<MessageData>
    val successLiveData: LiveData<Unit>
    val openAddScreenLiveData: LiveData<Unit>
    val openUpdateScreenLiveData: LiveData<Unit>
    val onClickLogoutLiveData: LiveData<Unit>
    val onClickDeleteAccountLiveData: LiveData<Unit>

    val listLiveData: LiveData<List<ContactEntity>>

    fun getAllContact()

    fun sync()

    fun delete(contactEntity: ContactEntity)

    fun deleteAccount()

    fun logout()
}