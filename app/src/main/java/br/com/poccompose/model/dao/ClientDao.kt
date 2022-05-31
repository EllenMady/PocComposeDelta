package br.com.poccompose.model.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.poccompose.model.entities.ClientEntity
import kotlinx.coroutines.flow.Flow

interface ClientDao {
    suspend fun update(clientEntity: ClientEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clientEntity: ClientEntity)
    fun getClients(): Flow<List<ClientEntity>>
}