package pe.edu.upc.services.impls;

import pe.edu.upc.client.ProfileClient;
import pe.edu.upc.entities.Subscription;
import pe.edu.upc.entities.Tip;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.model.Profile;
import pe.edu.upc.repositories.TipRepository;
import pe.edu.upc.services.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipServiceImpl implements TipService {

    @Autowired
    TipRepository tipRepository;

    @Qualifier("pe.edu.upc.client.ProfileClient")
    @Autowired
    ProfileClient profileClient;


    @Override
    public List<Tip> findAll() throws Exception
    {
        return tipRepository.findAll();
    }

    @Override
    public Tip createTip(Tip tip) {
        return tipRepository.save(tip);
    }

    @Override
    public Tip getTipById(Long aLong){
        Tip tip = tipRepository.findById(aLong).orElse(null);
        if (null!=tip){
            Profile chef = profileClient.getProfile(tip.getChefId()).getBody();
            tip.setChef(chef);

            Profile reader = profileClient.getProfile(tip.getReaderId()).getBody();
            tip.setReader(reader);
        }
        return tip;
    }

    @Override
    public List<Tip> getAllTipsByChefId(Long chefId) {
        return tipRepository.findTipsByChefId(chefId).orElse(null);
    }
}
