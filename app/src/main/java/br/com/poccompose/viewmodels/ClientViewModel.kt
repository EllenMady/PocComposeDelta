package br.com.poccompose.viewmodels

import androidx.lifecycle.ViewModel
import br.com.poccompose.model.entities.ClientEntity
import br.com.poccompose.repositories.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val clientRepository: ClientRepository
) : ViewModel() {

    suspend fun save(clientEntity: ClientEntity){
        clientRepository.save(clientEntity)
    }
}