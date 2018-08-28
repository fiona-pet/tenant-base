package com.fionapet.tenant.web;

import java.util.Optional;

import com.fionapet.tenant.domain.Demo;
import com.fionapet.tenant.domain.User;
import com.fionapet.tenant.security.CurrentUser;
import com.fionapet.tenant.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fionapet.tenant.repository.filter.DemoFilter;

@RestController
@RequestMapping("/api/demos")
public class DemoResource {

    private static final Logger log = LoggerFactory.getLogger(DemoResource.class);

    @Autowired
    private DemoService service;

    @PostMapping
    public ResponseEntity<Demo> save(@RequestBody Demo demo) {
    	log.debug("HTTP Post {}", demo);
    	if (demo.getId() != null) {
    		return ResponseEntity.badRequest().body(demo);
    	}
        demo = service.save(demo);
        return ResponseEntity.ok(demo);
    }
    
    @PutMapping
    public ResponseEntity<Demo> update(@RequestBody Demo demo) {
    	log.debug("HTTP Put {}", demo);
    	if (demo.getId() == null) {
    		return ResponseEntity.badRequest().body(demo);
    	}
        demo = service.save(demo);
        return ResponseEntity.ok(demo);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Demo demo) {
    	log.debug("HTTP Delete {}", demo);
        service.delete(demo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demo> findById(@PathVariable Long id) {
    	log.debug("HTTP Get id={}", id);
        Optional<Demo> demo = service.findById(id);
        return demo.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Page<Demo> findAll(@CurrentUser User usuario, Pageable pageable) {
    	log.debug("HTTP Get all Demo by usuario={} with page={}", usuario, pageable);
        Page<Demo> page = service.findAll(pageable);
        return page;
    }

    @PostMapping("/filter")
    public Page<Demo> findAllByFilter(@RequestBody(required=false) DemoFilter filter, @CurrentUser User usuario, Pageable pageable) {
    	log.debug("HTTP Get all by filter={}, usuario={}, page={}", filter, usuario, pageable);
        Page<Demo> page = service.findAll(filter, pageable);
        return page;
    }

}
