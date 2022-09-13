package uz.gita.newcontactapp.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.newcontactapp.data.local.entity.ContactEntity


interface AddScreenViewModel {

    val errorLiveData: LiveData<String>

    val successLiveData: LiveData<String>

    val openScreenLiveData: LiveData<Unit>

    fun add(contactEntity: ContactEntity)
}