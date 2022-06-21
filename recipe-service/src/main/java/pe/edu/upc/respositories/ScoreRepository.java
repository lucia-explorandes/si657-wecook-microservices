package pe.edu.upc.respositories;

import pe.edu.upc.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {/*
    List<Score> findBy(Long recipeId);*/
}
