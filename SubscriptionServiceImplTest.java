package com.example.subscriptionservice;

import com.example.subscriptionservice.client.ProfileClient;
import com.example.subscriptionservice.entities.Subscription;
import com.example.subscriptionservice.entities.Tip;
import com.example.subscriptionservice.repositories.SubscriptionRepository;
import com.example.subscriptionservice.services.SubscriptionService;
import com.example.subscriptionservice.services.impls.SubscriptionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SubscriptionServiceImplTest {

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @Qualifier("com.example.subscriptionservice.client.ProfileClient")
    @MockBean
    ProfileClient profileClient;

    @Autowired
    private SubscriptionService subscriptionService;


    @TestConfiguration
    static class SubscriptionServiceImplTestConfiguration{
        @Bean
        public SubscriptionService subscriptionService(){return new SubscriptionServiceImpl();
        };
    }

    @Test
    @DisplayName("When create Subscription with valid data then returns Subscription")
    public void WhenCreateSubscriptionWithValidDataThenReturnsSubscription(){
        //Arrange
        double amount = 0;
        Subscription subscription = new Subscription().setId(1L);
        when(subscriptionRepository.save(subscription)).thenAnswer(i->i.getArguments()[0]);

        //Act
        Subscription foundSubscription = subscriptionService.createSubscription(subscription);

        //Assert
        assertThat(foundSubscription).isEqualTo(subscription);
    }
}
