package com.example.eco_track_backend.repository;

import com.example.eco_track_backend.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findRoutesByName(String routeName);

    Optional<Route> findRouteByName(String routeName);

    @Query("select r.truckDriver from Route r where r.name = :name")
    List<Route> findDriverIdByName(@Param("name") String routeName);
}
