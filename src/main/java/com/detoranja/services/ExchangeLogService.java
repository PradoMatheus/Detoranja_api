package com.detoranja.services;

import com.detoranja.models.ExchangeLogModel;
import com.detoranja.models.ExchangeModel;
import com.detoranja.repositories.ExchangeLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExchangeLogService {

    final ExchangeLogRepository exchangeLogRepository;

    public ExchangeLogService(ExchangeLogRepository exchangeLogRepository) {
        this.exchangeLogRepository = exchangeLogRepository;
    }

    public ExchangeLogModel save(ExchangeLogModel exchangeLogModel) {
        return exchangeLogRepository.save(exchangeLogModel);
    }

    public List<ExchangeLogModel> findAll() {
        return exchangeLogRepository.findAll();
    }

    public Optional<ExchangeLogModel> findById(UUID id) {
        return exchangeLogRepository.findById(id);
    }

    public void delete(ExchangeLogModel exchangeLogModel) {
        exchangeLogRepository.delete(exchangeLogModel);
    }
}
