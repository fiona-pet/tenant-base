package com.fionapet.tenant.web;

import javax.validation.Valid;

import com.fionapet.tenant.domain.User;
import com.fionapet.tenant.security.JwtTokenProvider;
import com.fionapet.tenant.service.UserService;
import com.fionapet.tenant.web.dto.ApiResponse;
import com.fionapet.tenant.web.dto.LoginRequest;
import com.fionapet.tenant.web.dto.RegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private static final Logger log = LoggerFactory.getLogger(AuthResource.class);

    @Autowired
    private UserService usuarioService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody @Valid RegisterRequest registerRequest) {
    	log.debug("HTTP request to create new user {}", registerRequest);    	
    	try {
    		User usuario = usuarioService.create(registerRequest);
    		return ResponseEntity.ok(new ApiResponse(true, "User '" + usuario.getUsername() + "' created successfully!"));
    	} catch (Exception ex) {
    		return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
    	}
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
    	log.debug("HTTP request to log in {}", loginRequest);
    	Authentication authentication = authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
    	);
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	String jwt = tokenProvider.generateToken(authentication);
    	return ResponseEntity.ok(jwt);
    }

}
