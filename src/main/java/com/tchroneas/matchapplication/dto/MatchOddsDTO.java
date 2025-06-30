package com.tchroneas.matchapplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MatchOddsDTO {


    private Long id;

    @NotNull(message = "Match ID is required")
    private Long matchId;

    @NotBlank(message = "Specifier is required")
    @Pattern(regexp = "1|X|2", message = "Specifier must be one of: 1, X, 2")
    private String specifier;

    @NotBlank(message = "Odd is required")
    @Pattern(
            regexp = "^\\d+(\\.\\d{1,5})?$",
            message = "Odd must be a valid decimal number (e.g. 1.50)"
    )
    private String odd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getSpecifier() {
        return specifier;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public String getOdd() {
        return odd;
    }

    public void setOdd(String odd) {
        this.odd = odd;
    }
}
