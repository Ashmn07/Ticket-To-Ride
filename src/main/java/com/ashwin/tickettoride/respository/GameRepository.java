package com.ashwin.tickettoride.respository;

import com.ashwin.tickettoride.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    Optional<Game> findById(String id);

    List<Game> findAll();
}
