package es.ssdd.PracticaSSDD.repositories;

import es.ssdd.PracticaSSDD.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
