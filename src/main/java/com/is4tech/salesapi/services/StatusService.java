package com.is4tech.salesapi.services;

import com.is4tech.salesapi.models.Status;
import com.is4tech.salesapi.repositories.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status getById(Integer id) {
        return statusRepository.getOne(id);
    }
}
