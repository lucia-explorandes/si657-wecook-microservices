package pe.edu.upc.services.impls;

import pe.edu.upc.client.ProfileClient;
import pe.edu.upc.entities.Subscription;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.model.Profile;
import pe.edu.upc.repositories.SubscriptionRepository;
import pe.edu.upc.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Qualifier("pe.edu.upc.client.ProfileClient")
    @Autowired
    ProfileClient profileClient;


    @Override
    public List<Subscription> findAll() throws Exception
    {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
       return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getSubscriptionById(Long aLong){
        Subscription subscription = subscriptionRepository.findById(aLong).orElse(null);
        if (null!=subscription){
            Profile chef = profileClient.getProfile(subscription.getChefId()).getBody();
            subscription.setChef(chef);

            Profile reader = profileClient.getProfile(subscription.getReaderId()).getBody();
            subscription.setReader(reader);
        }
        return subscription;
    }

    @Override
    public List<Subscription> getAllSubscriptionsByChefId(Long chefId) {
        return subscriptionRepository.findSubscriptionsByChefId(chefId).orElse(null);
    }
}
