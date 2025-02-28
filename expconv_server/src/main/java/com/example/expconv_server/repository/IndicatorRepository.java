package com.example.expconv_server.repository;

import com.example.expconv_server.domain.indicator.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
}
