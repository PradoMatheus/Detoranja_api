package com.detoranja.services;

import com.detoranja.models.ClientContactModel;
import com.detoranja.repositories.ClientContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientContactService {

    final ClientContactRepository clientContactRepository;

    public ClientContactService(ClientContactRepository clientContactRepository) {
        this.clientContactRepository = clientContactRepository;
    }

    public ClientContactModel save(ClientContactModel clientContactModel) {
        return clientContactRepository.save(clientContactModel);
    }

    public List<ClientContactModel> findAll() {
        return clientContactRepository.findAll();
    }

    public Optional<ClientContactModel> findById(UUID id) {
        return clientContactRepository.findById(id);
    }

    public void delete(ClientContactModel clientContactModel) {
        clientContactRepository.delete(clientContactModel);
    }
}
