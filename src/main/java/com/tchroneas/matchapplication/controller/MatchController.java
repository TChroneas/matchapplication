package com.tchroneas.matchapplication.controller;

import com.tchroneas.matchapplication.dto.MatchDTO;
import com.tchroneas.matchapplication.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@Validated
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get match by id")
    public MatchDTO getMatch(@PathVariable Long id) {
        return matchService.getMatch(id);
    }

    @GetMapping
    @Operation(summary = "Get all matches")
    public List<MatchDTO> getAllMatches() {
        return matchService.getAllMatches();
    }

    @PostMapping
    @Operation(summary = "Create new match")
    public MatchDTO createMatch(@RequestBody @Valid MatchDTO dto) {
        return matchService.createMatch(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update match")
    public MatchDTO updateMatch(@PathVariable Long id, @RequestBody @Valid MatchDTO dto) {
        return matchService.updateMatch(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete match")
    public void deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
    }
}
