package com.example.expconv_server.repository;

import com.example.expconv_server.domain.scale.Scale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScaleRepository extends JpaRepository<Scale, Long> {
}
