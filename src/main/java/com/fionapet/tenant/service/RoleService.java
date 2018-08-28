package com.fionapet.tenant.service;

import java.util.List;
import java.util.Optional;

import com.fionapet.tenant.exception.RoleAlreadyExistsException;
import com.fionapet.tenant.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fionapet.tenant.domain.Role;

@Service
public class RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role create(Role role) {
        log.debug("Creating new Role {}...", role);
        if (roleRepository.existsByName(role.getName())) {
        	throw new RoleAlreadyExistsException("Role '" + role.getName() + "' already exists!");
        }
        role.setId(null);
        role = roleRepository.save(role);
        log.debug("Saved Role {}!", role);
        return role;
    }

    public Optional<Role> findById(Long id) {
        log.debug("Finding Role by id {}...", id);
        Optional<Role> role = roleRepository.findById(id);
        log.debug("Found Role {}!", role);
        return role;
    }
    
    public Optional<Role> findByNome(String nome) {
    	log.debug("Finding Role by name {}...", nome);
        Optional<Role> role = roleRepository.findByName(nome);
        log.debug("Found Role {}", role);
        return role;
    }
    
    public List<Role> findAll() {
    	log.debug("Finding all Role");
    	List<Role> all = roleRepository.findAll();
    	log.debug("Found all Role {}", all);
    	return all;
    }

}
