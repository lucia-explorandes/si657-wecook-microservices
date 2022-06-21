package pe.edu.upc.services;

import pe.edu.upc.entities.Subscription;
import pe.edu.upc.entities.Tip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TipService {

    List<Tip> findAll() throws Exception;

    Tip createTip(Tip tip);

    Tip getTipById(Long tipId);

    List<Tip> getAllTipsByChefId(Long chefId);
}
