package com.audita3077.ayodoa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.audita3077.ayodoa.model.Doa

@Database(entities = [Doa::class], version = 1, exportSchema = false)
abstract class DoaDatabase : RoomDatabase() {
    abstract val dao : DoaDao

    companion object {
        @Volatile
        private var INSTANCE: DoaDatabase? = null

        fun getInstance(context: Context): DoaDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DoaDatabase::class.java,
                        "doa_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
