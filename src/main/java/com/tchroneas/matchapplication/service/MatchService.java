package com.tchroneas.matchapplication.service;

import com.tchroneas.matchapplication.domain.Match;
import com.tchroneas.matchapplication.domain.repository.MatchRepository;
import com.tchroneas.matchapplication.dto.MatchDTO;
import com.tchroneas.matchapplication.mappers.MatchMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    private final MatchMapper matchMapper;

    public MatchService(MatchRepository matchRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
    }

    public MatchDTO getMatch(Long id){
        return matchMapper.toDto(matchRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<MatchDTO> getAllMatches(){
        return matchRepository.findAll().stream().map(matchMapper::toDto).toList();
    }

    public MatchDTO createMatch(MatchDTO dto){
        Match match = matchMapper.toEntity(dto);
        match = matchRepository.save(match);
        return matchMapper.toDto(match);
    }

    public void deleteMatch(Long id){
        matchRepository.deleteById(id);
    }


    public MatchDTO updateMatch(Long id, MatchDTO dto) {
        Match current = matchRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        matchMapper.updateEntityFromDto(dto , current);
        Match saved = matchRepository.save(current);
        return matchMapper.toDto(saved);
    }


}
