package com.detoranja.services;

import com.detoranja.models.RoleModel;
import com.detoranja.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleModel save(RoleModel roleModel) {
        return roleRepository.save(roleModel);
    }

    public List<RoleModel> findAll() {
        return roleRepository.findAll();
    }

    public Optional<RoleModel> findById(UUID id) {
        return roleRepository.findById(id);
    }

    public void delete(RoleModel roleModel) {
        roleRepository.delete(roleModel);
    }
}
