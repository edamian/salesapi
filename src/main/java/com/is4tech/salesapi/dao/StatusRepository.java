package com.is4tech.salesapi.dao;

import com.is4tech.salesapi.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
