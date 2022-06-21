package pe.edu.upc.respositories;


import pe.edu.upc.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    public Optional<Ingredient> findByName(String name);
}