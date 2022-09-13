package uz.gita.newcontactapp.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newcontactapp.data.local.entity.ContactEntity
import uz.gita.newcontactapp.data.utils.ResultData
import uz.gita.newcontactapp.domain.repository.contact.ContactRepository
import uz.gita.newcontactapp.domain.usecase.ContactUseCase
import javax.inject.Inject

class ContactUseCaseImpl @Inject constructor(private val contactRepository: ContactRepository) :
    ContactUseCase {
    override fun addContact(contactEntity: ContactEntity): Flow<ResultData<Unit>> =
        contactRepository.addContact(contactEntity)

    override fun update(contactEntity: ContactEntity): Flow<ResultData<Unit>> =
        contactRepository.update(contactEntity)

    override fun sync(): Flow<ResultData<Unit>> = contactRepository.sync()

    override fun delete(contactEntity: ContactEntity): Flow<ResultData<Unit>> =
        contactRepository.delete(contactEntity)

    override fun getAllContacts(): Flow<ResultData<List<ContactEntity>>> =
        contactRepository.getAllContacts()
}