package uz.gita.newcontactapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.newcontactapp.data.local.entity.ContactEntity

@Dao
interface ContactDao : BaseDao<ContactEntity> {

    @Query("Select * From contact Where statusDelete = 0")
    fun getAllContact(): Flow<List<ContactEntity>>

    @Query("Select * From contact")
    fun getAllContacts(): List<ContactEntity>
}