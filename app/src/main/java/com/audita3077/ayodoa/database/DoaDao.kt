package com.audita3077.ayodoa.database

import androidx.room.*
import com.audita3077.ayodoa.model.Doa
import kotlinx.coroutines.flow.Flow

@Dao
interface DoaDao {

    @Query("SELECT * FROM doa_table ORDER BY id DESC")
    fun getAllDoa(): Flow<List<Doa>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(doa: Doa)

    @Update
    suspend fun update(doa: Doa)

    @Delete
    suspend fun delete(doa: Doa)

    @Query("SELECT * FROM doa_table WHERE id = :id")
    suspend fun getById(id: Int): Doa?
}