package br.com.poccompose.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val time: Long,
    val name: String,
    val phone: String
)
