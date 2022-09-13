package uz.gita.newcontactapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(tableName = "contact")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var phone: String,
    var statusAdd: Int,
    var statusUpdate: Int,
    var statusDelete: Int
) : Serializable