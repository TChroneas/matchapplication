package com.tchroneas.matchapplication.controller;

import com.tchroneas.matchapplication.dto.MatchOddsDTO;
import com.tchroneas.matchapplication.service.MatchOddsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/match-odds")
@Validated
public class MatchOddsController {

    private final MatchOddsService matchOddsService;

    public MatchOddsController(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    @GetMapping
    @Operation(summary = "Get all match odds")
    public List<MatchOddsDTO> getAll() {
        return matchOddsService.getAllOdds();
    }

    @GetMapping("/match/{matchId}")
    @Operation(summary = "Get all odds by match id")
    public List<MatchOddsDTO> getOddsByMatchId(@PathVariable Long matchId) {
        return matchOddsService.getOddsByMatchId(matchId);
    }

    @PostMapping("/bulk/{matchId}")
    @Operation(summary = "Create list of odds to match")
    public List<MatchOddsDTO> addOddsToMatch(
            @PathVariable Long matchId,
            @RequestBody List<@Valid MatchOddsDTO> oddsDtos
    ) {
        return matchOddsService.addOddsToMatch(matchId, oddsDtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get match odd by id")
    public MatchOddsDTO getById(@PathVariable Long id) {
        return matchOddsService.getOdds(id);
    }

    @PostMapping
    @Operation(summary = "Create match odd")
    public MatchOddsDTO create(@Valid @RequestBody MatchOddsDTO dto) {
        return matchOddsService.createOdds(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update match odd")
    public MatchOddsDTO update(@PathVariable Long id,
                               @Valid @RequestBody MatchOddsDTO dto) {
        return matchOddsService.updateOdds(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete match odd")
    public void delete(@PathVariable Long id) {
        matchOddsService.deleteOdds(id);
    }
}
