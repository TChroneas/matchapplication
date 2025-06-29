package com.tchroneas.matchapplication.controller;

import com.tchroneas.matchapplication.dto.MatchOddsDTO;
import com.tchroneas.matchapplication.service.MatchOddsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/match-odds")
public class MatchOddsController {

    private final MatchOddsService matchOddsService;

    public MatchOddsController(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    @GetMapping
    public List<MatchOddsDTO> getAll() {
        return matchOddsService.getAllOdds();
    }

    @GetMapping("/match/{matchId}")
    public List<MatchOddsDTO> getOddsByMatchId(@PathVariable Long matchId) {
        return matchOddsService.getOddsByMatchId(matchId);
    }

    @PostMapping("/bulk/{matchId}")
    public List<MatchOddsDTO> addOddsToMatch(
            @PathVariable Long matchId,
            @RequestBody @Valid List<@Valid MatchOddsDTO> oddsDtos
    ) {
        return matchOddsService.addOddsToMatch(matchId, oddsDtos);
    }

    @GetMapping("/{id}")
    public MatchOddsDTO getById(@PathVariable Long id) {
        return matchOddsService.getOdds(id);
    }

    @PostMapping
    public MatchOddsDTO create(@Valid @RequestBody MatchOddsDTO dto) {
        return matchOddsService.createOdds(dto);
    }

    @PutMapping("/{id}")
    public MatchOddsDTO update(@PathVariable Long id,
                               @Valid @RequestBody MatchOddsDTO dto) {
        return matchOddsService.updateOdds(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        matchOddsService.deleteOdds(id);
    }
}
