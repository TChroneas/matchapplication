package com.tchroneas.matchapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tchroneas.matchapplication.helpers.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class MatchDTO {

    private Long id;

    private String description;

    @NotNull(message = "Match date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(
            type = "string",
            pattern = "dd-MM-yyyy",
            example = "31-12-2025",
            description = "Date in dd-MM-yyyy format"
    )
    private LocalDate matchDate;

    @NotBlank(message = "Match time is required")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Match time must be in HH:mm format")
    private String matchTime;

    @NotBlank(message = "Team A is required")
    private String team_a;

    @NotBlank(message = "Team B is required")
    private String team_b;

    @NotNull(message = "Sport is required")
    private Sport sport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getTeam_a() {
        return team_a;
    }

    public void setTeam_a(String team_a) {
        this.team_a = team_a;
    }

    public String getTeam_b() {
        return team_b;
    }

    public void setTeam_b(String team_b) {
        this.team_b = team_b;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
