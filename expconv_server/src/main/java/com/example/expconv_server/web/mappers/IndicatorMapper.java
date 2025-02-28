package com.example.expconv_server.web.mappers;

import com.example.expconv_server.domain.indicator.Indicator;
import com.example.expconv_server.web.dto.indicator.IndicatorDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndicatorMapper {
    IndicatorDto toDto(Indicator task);
    List<IndicatorDto> toDto(List<Indicator> tasks);
    Indicator toEntity(IndicatorDto taskDto);
}
