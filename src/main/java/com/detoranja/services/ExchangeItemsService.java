package com.detoranja.services;

import com.detoranja.models.ExchangeItemsModel;
import com.detoranja.models.ExchangeModel;
import com.detoranja.repositories.ExchangeItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExchangeItemsService {

    final ExchangeItemsRepository exchangeItemsRepository;

    public ExchangeItemsService(ExchangeItemsRepository exchangeItemsRepository) {
        this.exchangeItemsRepository = exchangeItemsRepository;
    }

    public ExchangeItemsModel save(ExchangeItemsModel exchangeItemsModel) {
        return exchangeItemsRepository.save(exchangeItemsModel);
    }

    public List<ExchangeItemsModel> findAll() {
        return exchangeItemsRepository.findAll();
    }

    public Optional<ExchangeItemsModel> findById(UUID id) {
        return exchangeItemsRepository.findById(id);
    }

    public void delete(ExchangeItemsModel exchangeItemsModel) {
        exchangeItemsRepository.delete(exchangeItemsModel);
    }
}
