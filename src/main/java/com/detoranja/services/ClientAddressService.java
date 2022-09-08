package com.detoranja.services;

import com.detoranja.models.ClientAddressModel;
import com.detoranja.repositories.ClientAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientAddressService {

    final ClientAddressRepository clientAddressRepository;

    public ClientAddressService(ClientAddressRepository clientAddressRepository) {
        this.clientAddressRepository = clientAddressRepository;
    }

    public ClientAddressModel save(ClientAddressModel clientAddressModel) {
        return clientAddressRepository.save(clientAddressModel);
    }

    public List<ClientAddressModel> findAll() {
        return clientAddressRepository.findAll();
    }

    public Optional<ClientAddressModel> findById(UUID id) {
        return clientAddressRepository.findById(id);
    }

    public void delete(ClientAddressModel clientAddressModel) {
        clientAddressRepository.delete(clientAddressModel);
    }
}
