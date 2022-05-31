package br.com.poccompose.repositories

import br.com.poccompose.model.dao.ClientDao
import br.com.poccompose.model.entities.ClientEntity
import javax.inject.Inject

class ClientRepository @Inject constructor(
    private val clientDao: ClientDao
) {
    fun getClients() = clientDao.getClients()

    suspend fun save(clientEntity: ClientEntity){
        clientDao.insert(clientEntity)
    }
}