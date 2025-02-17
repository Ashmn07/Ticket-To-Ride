package com.ashwin.tickettoride.respository;

import com.ashwin.tickettoride.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findById(String id);
    List<Player> findByGameId(String gameId);
}
