package com.tchroneas.matchapplication.service;

import com.tchroneas.matchapplication.domain.Match;
import com.tchroneas.matchapplication.domain.MatchOdds;
import com.tchroneas.matchapplication.domain.repository.MatchOddsRepository;
import com.tchroneas.matchapplication.domain.repository.MatchRepository;
import com.tchroneas.matchapplication.dto.MatchOddsDTO;
import com.tchroneas.matchapplication.mappers.MatchOddsMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchOddsService {

    private final MatchOddsRepository matchOddsRepository;
    private final MatchRepository matchRepository;
    private final MatchOddsMapper matchOddsMapper;

    public MatchOddsService(MatchOddsRepository matchOddsRepository,
                            MatchRepository matchRepository,
                            MatchOddsMapper matchOddsMapper) {
        this.matchOddsRepository = matchOddsRepository;
        this.matchRepository = matchRepository;
        this.matchOddsMapper = matchOddsMapper;
    }

    public List<MatchOddsDTO> getAllOdds() {
        return matchOddsRepository.findAll().stream()
                .map(matchOddsMapper::toDto)
                .toList();
    }

    public MatchOddsDTO getOdds(Long id) {
        MatchOdds odds = matchOddsRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return matchOddsMapper.toDto(odds);
    }

    public MatchOddsDTO createOdds(MatchOddsDTO dto) {
        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(EntityNotFoundException::new);
        MatchOdds entity = matchOddsMapper.toEntity(dto);
        entity.setMatch(match);
        return matchOddsMapper.toDto(matchOddsRepository.save(entity));
    }

    public MatchOddsDTO updateOdds(Long id, MatchOddsDTO dto) {
        MatchOdds existing = matchOddsRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        matchOddsMapper.updateEntityFromDto(dto,existing);
        return matchOddsMapper.toDto(matchOddsRepository.save(existing));
    }

    public void deleteOdds(Long id) {
        matchOddsRepository.deleteById(id);
    }


    public List<MatchOddsDTO> getOddsByMatchId(Long matchId) {
        List<MatchOdds> oddsList = matchOddsRepository.findByMatch_Id(matchId);
        return oddsList.stream().map(matchOddsMapper::toDto).toList();
    }

    public List<MatchOddsDTO> addOddsToMatch(Long matchId, List<MatchOddsDTO> dtoList) {
        Match match = matchRepository.findById(matchId).orElseThrow(EntityNotFoundException::new);

        List<MatchOdds> odds = dtoList.stream()
                .map(dto -> {
                    MatchOdds entity = matchOddsMapper.toEntity(dto);
                    entity.setMatch(match);
                    return entity;
                }).toList();

        List<MatchOdds> saved = matchOddsRepository.saveAll(odds);
        return saved.stream().map(matchOddsMapper::toDto).toList();
    }
}
