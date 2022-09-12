package com.detoranja.controllers;

import com.detoranja.dtos.ExchangeDto;
import com.detoranja.dtos.RoleDto;
import com.detoranja.models.ExchangeModel;
import com.detoranja.models.RoleModel;
import com.detoranja.services.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/role")
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Object> saveRole(@RequestBody @Valid RoleDto roleDto) {
        var roleModel = new RoleModel();
        BeanUtils.copyProperties(roleDto, roleModel);
        return ResponseEntity.status(HttpStatus.OK).body(roleService.save(roleModel));
    }

    @GetMapping
    public ResponseEntity<List<RoleModel>> getAllRole() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneRole(@PathVariable(value = "id") UUID id) {
        var roleModelOptional = roleService.findById(id);
        if (!roleModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        return ResponseEntity.status(HttpStatus.OK).body(roleModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable(value = "id") UUID id) {
        var roleModelOptional = roleService.findById(id);
        if (!roleModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        roleService.delete(roleModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Role deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateRole(@RequestBody @Valid RoleDto roleDto, @PathVariable(value = "id") UUID id) {
        var roleModelOptional = roleService.findById(id);
        if (!roleModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        var roleModel = roleModelOptional.get();
        BeanUtils.copyProperties(roleDto, roleModel);
        return ResponseEntity.status(HttpStatus.OK).body(roleService.save(roleModel));
    }
}
