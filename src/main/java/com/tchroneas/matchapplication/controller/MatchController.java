package com.tchroneas.matchapplication.controller;

import com.tchroneas.matchapplication.dto.MatchDTO;
import com.tchroneas.matchapplication.service.MatchService;
import jakarta.validation.Valid;
import org.hibernate.internal.build.AllowNonPortable;
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
    public MatchDTO getMatch(@PathVariable Long id) {
        return matchService.getMatch(id);
    }

    @GetMapping
    public List<MatchDTO> getAllMatches() {
        return matchService.getAllMatches();
    }

    @PostMapping
    public MatchDTO createMatch(@RequestBody @Valid MatchDTO dto) {
        return matchService.createMatch(dto);
    }

    @PutMapping("/{id}")
    public MatchDTO updateMatch(@PathVariable Long id, @RequestBody @Valid MatchDTO dto) {
        return matchService.updateMatch(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
    }
}
