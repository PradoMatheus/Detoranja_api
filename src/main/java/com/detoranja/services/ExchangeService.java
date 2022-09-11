package com.detoranja.services;

import com.detoranja.models.CartModel;
import com.detoranja.models.ExchangeModel;
import com.detoranja.repositories.ExchangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExchangeService {

    final ExchangeRepository exchangeRepository;

    public ExchangeService(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    public ExchangeModel save(ExchangeModel exchangeModel) {
        return exchangeRepository.save(exchangeModel);
    }

    public List<ExchangeModel> findAll() {
        return exchangeRepository.findAll();
    }

    public Optional<ExchangeModel> findById(UUID id) {
        return exchangeRepository.findById(id);
    }

    public void delete(ExchangeModel exchangeModel) {
        exchangeRepository.delete(exchangeModel);
    }
}
