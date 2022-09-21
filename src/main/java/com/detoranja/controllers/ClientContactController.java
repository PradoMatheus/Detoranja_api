package com.detoranja.controllers;

import com.detoranja.dtos.ClientContactDto;
import com.detoranja.models.ClientContactModel;
import com.detoranja.services.ClientContactService;
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
@RequestMapping(value = "/client/contact")
public class ClientContactController {

    final ClientContactService clientContactService;
    final ClientService clientService;

    public ClientContactController(ClientContactService clientContactService, ClientService clientService) {
        this.clientContactService = clientContactService;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Object> saveClientContact(@RequestBody @Valid ClientContactDto clientContactDto) {
        var clientContactModel = new ClientContactModel();
        if (clientService.findById(clientContactDto.getClientModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        BeanUtils.copyProperties(clientContactDto, clientContactModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientContactService.save(clientContactModel));
    }

    @GetMapping
    public ResponseEntity<List<ClientContactModel>> getAllClientContact() {
        return ResponseEntity.status(HttpStatus.OK).body(clientContactService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneClientContact(@PathVariable(value = "id") UUID id) {
        var clientContactModelOptional = clientContactService.findById(id);
        if (!clientContactModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Contact not found");
        return ResponseEntity.status(HttpStatus.OK).body(clientContactModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClientContact(@PathVariable(value = "id") UUID id) {
        var clientContactModelOptional = clientContactService.findById(id);
        if (!clientContactModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Contact not found");
        clientContactService.delete(clientContactModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client Contact deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateClientContact(@RequestBody @Valid ClientContactDto clientContactDto, @PathVariable(value = "id") UUID id) {
        var clientContactModelOptional = clientContactService.findById(id);
        if (!clientContactModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Contact not found");
        if (clientService.findById(clientContactDto.getClientModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        var clientContactModel = clientContactModelOptional.get();
        BeanUtils.copyProperties(clientContactDto, clientContactModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientContactService.save(clientContactModel));
    }
}
