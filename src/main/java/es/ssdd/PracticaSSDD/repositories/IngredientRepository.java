package es.ssdd.PracticaSSDD.repositories;

import es.ssdd.PracticaSSDD.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
