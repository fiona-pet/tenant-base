package com.fionapet.tenant.service;

import com.fionapet.tenant.domain.Demo;
import com.fionapet.tenant.repository.DemoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fionapet.tenant.repository.filter.DemoFilter;
import com.fionapet.tenant.repository.filter.DemoSpecification;

import java.util.Optional;

@Service
public class DemoService {

    private static final Logger log = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    private DemoRepository demoRepository;

    @Transactional
    public Demo save(Demo demo) {
        log.debug("Saving Demo {}...", demo);
        demo = demoRepository.save(demo);
        log.debug("Demo saved {}!", demo);
        return demo;
    }

    @Transactional
    public void delete(Demo demo) {
        log.debug("Deleting Demo {}...", demo);
        demoRepository.delete(demo);
        log.debug("Deleted Demo {}", demo);
    }

    public Optional<Demo> findById(Long id) {
        log.debug("Finding Demo by id {}...", id);
        Optional<Demo> demo = demoRepository.findById(id);
        log.debug("Found Demo {}!", demo);
        return demo;
    }

    public Page<Demo> findAll(Pageable pageable) {
        return findAll(null, pageable);
    }

    public Page<Demo> findAll(DemoFilter filter, Pageable pageable) {
        log.debug("Finding all Demo by filter {} and page {}...", filter, pageable);
        Specification<Demo> specification = DemoSpecification.build(filter);
        Page<Demo> page = demoRepository.findAll(specification, pageable);
        log.debug("Found all by filter {}!", page);
        return page;
    }

}
