package com.is4tech.salesapi.services;

import com.is4tech.salesapi.dao.StatusRepository;
import com.is4tech.salesapi.domain.Status;
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
