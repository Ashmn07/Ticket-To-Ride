package com.ashwin.tickettoride.response;

import com.ashwin.tickettoride.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameSummaryResponse {
    private String id;
    private GameStatus status;
    private int playerCount;
    private LocalDateTime createdAt;
}