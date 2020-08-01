package com.is4tech.salesapi.repositories;

import com.is4tech.salesapi.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
