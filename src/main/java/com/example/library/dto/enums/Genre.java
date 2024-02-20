package com.example.library.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {
    FANTASY("FANTASY"),
    SCIENCE_FICTION("SCIENCE_FICTION"),
    ROMANCE("ROMANCE"),
    MYSTERY("MYSTERY"),
    HORROR("HORROR"),
    HISTORICAL_FICTION("HISTORICAL_FICTION"),
    THRILLER("THRILLER"),
    ADVENTURE("ADVENTURE"),
    NON_FICTION("NON_FICTION"),
    POETRY("POETRY");

    private final String displayName;
}

