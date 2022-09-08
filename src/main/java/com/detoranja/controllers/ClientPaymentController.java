package com.detoranja.controllers;

import com.detoranja.dtos.ClientPaymentDto;
import com.detoranja.models.ClientPaymentModel;
import com.detoranja.services.ClientPaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/client/payment")
public class ClientPaymentController {

    final ClientPaymentService clientPaymentService;

    public ClientPaymentController(ClientPaymentService clientPaymentService) {
        this.clientPaymentService = clientPaymentService;
    }

    @PostMapping
    public ResponseEntity<Object> saveClientPayment(@RequestBody @Valid ClientPaymentDto clientPaymentDto) {
        var clientPaymentModel = new ClientPaymentModel();
        BeanUtils.copyProperties(clientPaymentDto, clientPaymentModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientPaymentService.save(clientPaymentModel));
    }

    @GetMapping
    public ResponseEntity<List<ClientPaymentModel>> getAllClientPayment() {
        return ResponseEntity.status(HttpStatus.OK).body(clientPaymentService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneClientPayment(@PathVariable(value = "id") UUID id) {
        var clientPaymentModelOptional = clientPaymentService.findById(id);
        if (!clientPaymentModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Payment not found");
        return ResponseEntity.status(HttpStatus.OK).body(clientPaymentModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClientPayment(@PathVariable(value = "id") UUID id) {
        var clientPaymentModelOptional = clientPaymentService.findById(id);
        if (!clientPaymentModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Payment not found");
        clientPaymentService.delete(clientPaymentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client Contact deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateClientPayment(@RequestBody @Valid ClientPaymentDto clientPaymentDto, @PathVariable(value = "id") UUID id) {
        var clientPaymentModelOptional = clientPaymentService.findById(id);
        if (!clientPaymentModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Payment not found");
        var clientPaymentModel = clientPaymentModelOptional.get();
        BeanUtils.copyProperties(clientPaymentDto, clientPaymentModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientPaymentService.save(clientPaymentModel));
    }
}
