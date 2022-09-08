package com.detoranja.services;

import com.detoranja.models.ClientContactModel;
import com.detoranja.models.ClientPaymentModel;
import com.detoranja.repositories.ClientPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientPaymentService {

    final ClientPaymentRepository clientPaymentRepository;

    public ClientPaymentService(ClientPaymentRepository clientPaymentRepository) {
        this.clientPaymentRepository = clientPaymentRepository;
    }

    public ClientPaymentModel save(ClientPaymentModel clientPaymentModel) {
        return clientPaymentRepository.save(clientPaymentModel);
    }

    public List<ClientPaymentModel> findAll() {
        return clientPaymentRepository.findAll();
    }

    public Optional<ClientPaymentModel> findById(UUID id) {
        return clientPaymentRepository.findById(id);
    }

    public void delete(ClientPaymentModel clientPaymentModel) {
        clientPaymentRepository.delete(clientPaymentModel);
    }
}
