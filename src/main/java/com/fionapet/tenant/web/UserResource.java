package com.fionapet.tenant.web;

import java.util.Optional;

import com.fionapet.tenant.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fionapet.tenant.service.UserService;

@RestController
@RequestMapping("/api/users")
@Secured("ROLE_ADMIN")
public class UserResource {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserService userService;
    
    @GetMapping
    public Page<User> findAll(Pageable pageable) {
    	log.debug("HTTP Get all User by page {}", pageable);
    	Page<User> page = userService.findAll(pageable);
    	return page;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
    	log.debug("HTTP Get User by username {}", username);
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
    	log.debug("HTTP Get User by email {}", email);
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
