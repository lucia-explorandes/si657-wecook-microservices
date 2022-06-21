package pe.edu.upc.respositories;


import pe.edu.upc.entities.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia,Long> {

}
