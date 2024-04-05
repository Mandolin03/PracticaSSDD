package es.ssdd.PracticaSSDD.repositories;

import es.ssdd.PracticaSSDD.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}