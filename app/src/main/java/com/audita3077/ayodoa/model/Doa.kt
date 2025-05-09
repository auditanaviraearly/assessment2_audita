package com.audita3077.ayodoa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doa_table")
data class Doa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val judul: String,
    val arab: String,
    val latin: String,
    val terjemahan: String
)
