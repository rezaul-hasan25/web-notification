package com.fsmms.web_notification.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService<T, R extends JpaRepository<T, Long>> {

    @Autowired
    private R repository;


    public T save(T entity) {
        return repository.save(entity);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    public T update(Long id, T updatedEntity) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found with ID: " + id);
        }
        return repository.save(updatedEntity);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}

