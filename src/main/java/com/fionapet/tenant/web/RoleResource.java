package com.fionapet.tenant.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fionapet.tenant.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fionapet.tenant.domain.Role;

@RestController
@RequestMapping("/api/roles")
@Secured("ROLE_ADMIN")
public class RoleResource {

    private static final Logger log = LoggerFactory.getLogger(RoleResource.class);

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> create(@Valid @RequestBody Role role) {
    	log.debug("HTTP Post Role {}", role);
    	if (role.getId() != null) {
    		return ResponseEntity.badRequest().body(role);
		}
		role = roleService.create(role);
        return ResponseEntity.ok(role);
    }
    
    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
    	log.debug("HTTP Get all Role");
        List<Role> all = roleService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Role> findByNome(@PathVariable String name) {
    	log.debug("HTTP Get Role by name {}", name);
        Optional<Role> role = roleService.findByNome(name);
        return role.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
}
