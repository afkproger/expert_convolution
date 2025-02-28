package com.example.expconv_server.web.mappers;

import com.example.expconv_server.domain.scale.Scale;
import com.example.expconv_server.web.dto.scale.ScaleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScaleMapper {
    ScaleDto toDto(Scale task);
    List<ScaleDto> toDto(List<Scale> tasks);
    Scale toEntity(ScaleDto taskDto);
}
