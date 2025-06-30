package com.tchroneas.matchapplication.mappers;

import com.tchroneas.matchapplication.domain.MatchOdds;
import com.tchroneas.matchapplication.dto.MatchOddsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface MatchOddsMapper {

    @Mapping(source = "match.id", target = "matchId")
    @Mapping(source = "odd", target = "odd", qualifiedByName = "bigDecimalToString")
    MatchOddsDTO toDto(MatchOdds entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "odd", target = "odd", qualifiedByName = "stringToBigDecimal")
    MatchOdds toEntity(MatchOddsDTO dto);

    @Named("bigDecimalToString")
    static String bigDecimalToString(BigDecimal value) {
        return value != null ? value.toPlainString() : null;
    }

    @Named("stringToBigDecimal")
    static BigDecimal stringToBigDecimal(String value) {
        return value != null ? new BigDecimal(value) : null;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "odd", target = "odd", qualifiedByName = "stringToBigDecimal")
    @Mapping(target = "match", ignore = true)
    void updateEntityFromDto(MatchOddsDTO dto, @MappingTarget MatchOdds entity);

}

