package com.detoranja.controllers;

import com.detoranja.dtos.ClientAddressDto;
import com.detoranja.models.ClientAddressModel;
import com.detoranja.services.ClientAddressService;
import com.detoranja.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/client/address")
public class ClientAddressController {

    final ClientAddressService clientAddressService;
    final ClientService clientService;

    public ClientAddressController(ClientAddressService clientAddressService, ClientService clientService) {
        this.clientAddressService = clientAddressService;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Object> saveClientAddress(@RequestBody @Valid ClientAddressDto clientAddressDto) {
        var clientAddressModel = new ClientAddressModel();
        if (clientService.findById(clientAddressDto.getClientModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        BeanUtils.copyProperties(clientAddressDto, clientAddressModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientAddressService.save(clientAddressModel));
    }

    @GetMapping
    public ResponseEntity<List<ClientAddressModel>> getAllClientAddress() {
        return ResponseEntity.status(HttpStatus.OK).body(clientAddressService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneClientAddress(@PathVariable(value = "id") UUID id) {
        var clientAddressModelOptional = clientAddressService.findById(id);
        if (!clientAddressModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Address not found");
        return ResponseEntity.status(HttpStatus.OK).body(clientAddressModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClientAddress(@PathVariable(value = "id") UUID id) {
        var clientAddressModelOptional = clientAddressService.findById(id);
        if (!clientAddressModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Address not found");
        clientAddressService.delete(clientAddressModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client Address deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateClientAddress(@RequestBody @Valid ClientAddressDto clientAddressDto, @PathVariable(value = "id") UUID id) {
        var clientAddressModelOptional = clientAddressService.findById(id);
        if (!clientAddressModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Address not found");
        if (clientService.findById(clientAddressDto.getClientModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        var clientAddressModel = clientAddressModelOptional.get();
        BeanUtils.copyProperties(clientAddressDto, clientAddressModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientAddressService.save(clientAddressModel));
    }
}
