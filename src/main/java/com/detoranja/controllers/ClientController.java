package com.detoranja.controllers;

import com.detoranja.dtos.ClientDto;
import com.detoranja.models.ClientModel;
import com.detoranja.services.ClientService;
import com.detoranja.services.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/client")
public class ClientController {

    final ClientService clientService;
    final CompanyService companyService;

    public ClientController(ClientService clientService, CompanyService companyService) {
        this.clientService = clientService;
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDto clientDto) {
        if (clientService.existByCpf(clientDto.getCpf()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Cpf is already in use.");
        if (companyService.findById(clientDto.getCompanyModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Company not found.");
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDto, clientModel);
        clientModel.setCreate_date(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
    }

    @GetMapping
    public ResponseEntity<List<ClientModel>> getAllClient() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") UUID id) {
        var clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        return ResponseEntity.status(HttpStatus.OK).body(clientModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") UUID id) {
        var clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        clientService.delete(clientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody @Valid ClientDto clientDto, @PathVariable(value = "id") UUID id) {
        var clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        if (companyService.findById(clientDto.getCompanyModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Company not found.");
        var clientModel = clientModelOptional.get();
        BeanUtils.copyProperties(clientDto, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
    }
}
