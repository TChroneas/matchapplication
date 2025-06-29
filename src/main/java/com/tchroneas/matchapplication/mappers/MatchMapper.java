package com.tchroneas.matchapplication.mappers;

import com.tchroneas.matchapplication.domain.Match;
import com.tchroneas.matchapplication.dto.MatchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    MatchDTO toDto(Match match);

    @Mapping(target = "id", ignore = true)
    Match toEntity(MatchDTO dto);


    @Mapping(target = "id", ignore = true) // prevent ID override
    void updateEntityFromDto(MatchDTO dto, @MappingTarget Match entity);
}
