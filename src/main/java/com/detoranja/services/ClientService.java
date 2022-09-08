package com.detoranja.services;

import com.detoranja.models.ClientModel;
import com.detoranja.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean existByCpf(long cpf) {
        return clientRepository.existsByCpf(cpf);
    }

    public ClientModel save(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public Optional<ClientModel> findById(UUID id) {
        return clientRepository.findById(id);
    }

    public void delete(ClientModel clientModel) {
        clientRepository.delete(clientModel);
    }
}
