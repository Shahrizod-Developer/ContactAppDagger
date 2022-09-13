package uz.gita.newcontactapp.domain.repository.contact.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newcontactapp.R
import uz.gita.newcontactapp.data.local.database.AppDatabase
import uz.gita.newcontactapp.data.local.entity.ContactEntity
import uz.gita.newcontactapp.data.local.shp.MySharedPreference
import uz.gita.newcontactapp.data.model.AuthData
import uz.gita.newcontactapp.data.remote.api.ContactApi
import uz.gita.newcontactapp.data.remote.request.AuthRequest
import uz.gita.newcontactapp.data.remote.request.ContactRequest
import uz.gita.newcontactapp.data.utils.MessageData
import uz.gita.newcontactapp.data.utils.ResultData
import uz.gita.newcontactapp.data.utils.hasConnection
import uz.gita.newcontactapp.domain.repository.contact.ContactRepository
import javax.inject.Inject
import kotlin.math.floor

class ContactRepositoryImpl @Inject constructor(private val contactApi: ContactApi) :
    ContactRepository {

    private val sharedPreference = MySharedPreference.getInstance()
    private val dao = AppDatabase.getInstance().contactDao()

    override fun addContact(contactEntity: ContactEntity): Flow<ResultData<Unit>> = flow {

        if (hasConnection()) {
            try {
                val response = contactApi.addContact(
                    sharedPreference.token,
                    ContactRequest(contactEntity.name, contactEntity.phone)
                )
                when (response.code()) {
                    in 200..299 -> {
                        val data = response.body()?.data
                        contactEntity.statusAdd = 0
                        Log.d("SSS", contactEntity.toString())
                        ContactEntity(
                            data?.id!!,
                            contactEntity.name,
                            contactEntity.phone,
                            0,
                            0,
                            0
                        )
                        dao.insert(contactEntity)
                        emit(ResultData.success(Unit))
                    }
                    else -> {
                        emit(
                            ResultData.message(
                                MessageData.messageText(
                                    response.errorBody()?.string().toString()
                                )
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(
                    ResultData.message(
                        MessageData.messageText(
                            e.message.toString()
                        )
                    )
                )
            }
        } else {
            contactEntity.statusAdd = 1
            dao.insert(contactEntity)
            emit(ResultData.success(Unit))
            emit(
                ResultData.message(
                    MessageData.messageText(
                        "Sizda hozir internet yoqilmagan. Bu contact local databasega saqlandi. Internet yoqib syn qilsangiz contact serverga saqlanadi Insha'Alloh"
                    )
                )
            )
        }
    }.catch { emit(ResultData.message(MessageData.messageResource(R.string.connection_error))) }
        .flowOn(Dispatchers.IO)

    override fun update(contactEntity: ContactEntity): Flow<ResultData<Unit>> = flow {

        if (hasConnection()) {
            try {
                val response = contactApi.updateContact(
                    sharedPreference.token,
                    contactEntity.id,
                    ContactRequest(
                        contactEntity.name, contactEntity.phone
                    )
                )
                when (response.code()) {
                    in 200..299 -> {
                        contactEntity.statusUpdate = 0
                        dao.update(contactEntity)
                        emit(ResultData.success(Unit))
                    }
                    else -> {
                        emit(
                            ResultData.message(
                                MessageData.messageText(
                                    response.errorBody()?.string().toString()
                                )
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(
                    ResultData.message(
                        MessageData.messageText(
                            e.message.toString()
                        )
                    )
                )
            }
        } else {
            contactEntity.statusUpdate = 1
            dao.update(contactEntity)
            emit(ResultData.success(Unit))
            emit(
                ResultData.message(
                    MessageData.messageText(
                        "Sizda hozir internet yoqilmagan. Bu contact local databaseda o'zgartirildi. Internet yoqib sync qilsangiz contact serverga saqlanadi Insha'Alloh"
                    )
                )
            )
        }
    }.catch { emit(ResultData.message(MessageData.messageResource(R.string.connection_error))) }
        .flowOn(Dispatchers.IO)

    override fun sync(): Flow<ResultData<Unit>> = flow<ResultData<Unit>> {


        if (hasConnection()) {

            val list = getAllContact()

            for (i in list.indices) {
                if (list[i].statusAdd == 1) {
                    Log.d("SSS", "Salom")
                    addContact(list[i])
                } else if (list[i].statusDelete == 1) {
                    delete(list[i])
                } else if (list[i].statusUpdate == 1) {
                    update(list[i])
                }
            }
            getAllContacts()
            emit(
                ResultData.message(
                    MessageData.messageText(
                        "Yangilandi"
                    )
                )
            )
        } else {
            emit(
                ResultData.message(
                    MessageData.messageText(
                        "Internet mavjud emas. Yoqib so'ngra sync qiling"
                    )
                )
            )
        }
    }.catch { emit(ResultData.message(MessageData.messageResource(R.string.connection_error))) }
        .flowOn(Dispatchers.IO)

    override fun delete(contactEntity: ContactEntity): Flow<ResultData<Unit>> = flow {

        if (hasConnection()) {
            try {
                val response = contactApi.deleteContact(
                    sharedPreference.token,
                    contactEntity.id
                )
                when (response.code()) {
                    in 200..299 -> {
                        val data = response.body()?.data?.id
                        dao.delete(
                            ContactEntity(
                                data!!,
                                contactEntity.name,
                                contactEntity.phone,
                                0,
                                0,
                                0
                            )
                        )
                        emit(ResultData.success(Unit))
                    }
                    else -> {
                        emit(
                            ResultData.message(
                                MessageData.messageText(
                                    response.errorBody()?.string().toString()
                                )
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(
                    ResultData.message(
                        MessageData.messageText(
                            e.message.toString()
                        )
                    )
                )
            }
        } else {
            contactEntity.statusDelete = 1
            dao.update(contactEntity)
            emit(ResultData.success(Unit))
            emit(
                ResultData.message(
                    MessageData.messageText(
                        "Sizda hozir internet yoqilmagan. Bu contact local databaseda o'chirilishi kerak bo'lganlar ro'yxatiga olindi. Internet yoqib sync qilsangiz contact serverga saqlanadi Insha'Alloh"
                    )
                )
            )
        }
    }.catch { emit(ResultData.message(MessageData.messageResource(R.string.connection_error))) }
        .flowOn(Dispatchers.IO)

    override fun getAllContact(): List<ContactEntity> = dao.getAllContacts()

    override fun getAllContacts(): Flow<ResultData<List<ContactEntity>>> =
        flow<ResultData<List<ContactEntity>>> {

            if (hasConnection()) {
                try {
                    val response = contactApi.getAllContacts(sharedPreference.token)

                    when (response.code()) {
                        in 200..299 -> {

                            dao.delete()
                            dao.insert(response.body()?.data!!.map {
                                ContactEntity(
                                    it.id,
                                    it.name,
                                    it.phone,
                                    0,
                                    0,
                                    0
                                )
                            })
                        }
                        else -> {
                            emit(
                                ResultData.message(
                                    MessageData.messageText(
                                        response.errorBody()?.string().toString()
                                    )
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    emit(
                        ResultData.message(
                            MessageData.messageText(
                                e.message.toString()
                            )
                        )
                    )
                }
            } else {
                ResultData.success(dao.getAllContacts()).onSuccess {
                    emit(ResultData.success(it))
                }
                emit(
                    ResultData.message(
                        MessageData.messageText(
                            "Internet mavjud emas"
                        )
                    )
                )
            }

            ResultData.success(dao.getAllContacts()).onSuccess {
                emit(ResultData.success(it))
            }
        }.catch { emit(ResultData.message(MessageData.messageResource(R.string.connection_error))) }
            .flowOn(Dispatchers.IO)
}