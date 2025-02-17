package com.ashwin.tickettoride.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerSummary {
    private String id;
    private String name;
    private int score;
    private boolean isReady;
}
