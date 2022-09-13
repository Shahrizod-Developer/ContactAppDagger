package uz.gita.newcontactapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.newcontactapp.data.local.entity.ContactEntity
import uz.gita.newcontactapp.data.utils.ResultData

interface ContactUseCase {

    fun addContact(contactEntity: ContactEntity): Flow<ResultData<Unit>>

    fun update(contactEntity: ContactEntity): Flow<ResultData<Unit>>

    fun sync(): Flow<ResultData<Unit>>

    fun delete(contactEntity: ContactEntity): Flow<ResultData<Unit>>

    fun getAllContacts(): Flow<ResultData<List<ContactEntity>>>
}