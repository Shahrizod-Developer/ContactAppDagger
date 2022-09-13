package uz.gita.newcontactapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.newcontactapp.data.local.dao.ContactDao
import uz.gita.newcontactapp.data.local.entity.ContactEntity


@Database(entities = [ContactEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {

        private var database: AppDatabase? = null

        fun init(context: Context) {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "contact"
                )
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getInstance() = database!!
    }
}