package com.example.mapper;

import com.example.dto.AvailableTagDto;
import com.example.model.AvailableTag;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AvailableTagMapper {

    @Mapping(target = "metric", source = "metric", ignore = true)
    AvailableTagDto toAvailableTagDto(AvailableTag availableTag);

    List<AvailableTagDto> convertAvailableTagListToAvailableTagDtoList(List<AvailableTag> list);

}