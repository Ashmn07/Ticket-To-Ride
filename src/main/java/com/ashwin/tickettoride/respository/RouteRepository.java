package com.ashwin.tickettoride.respository;

import com.ashwin.tickettoride.enums.City;
import com.ashwin.tickettoride.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, String> {
    List<Route> findByGameId(String gameId);
    List<Route> findByGameIdAndCityFromAndCityTo(String gameId, City cityFrom, City cityTo);
}
