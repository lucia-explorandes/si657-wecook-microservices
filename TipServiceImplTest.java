package com.example.subscriptionservice;

import com.example.subscriptionservice.client.ProfileClient;
import com.example.subscriptionservice.entities.Tip;
import com.example.subscriptionservice.repositories.TipRepository;
import com.example.subscriptionservice.services.TipService;
import com.example.subscriptionservice.services.impls.TipServiceImpl;
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
public class TipServiceImplTest {

    @MockBean
    private TipRepository tipRepository;

    @Qualifier("com.example.subscriptionservice.client.ProfileClient")
    @MockBean
    ProfileClient profileClient;
    @Autowired
    private TipService tipService;

    @TestConfiguration
    static class TipServiceImplTestConfiguration{
        @Bean
        public TipService tipService(){return new TipServiceImpl();}
    }

    @Test
    @DisplayName("When create Tip with valid data then returns Tip")
    public void WhenCreateTipWithValidDataThenReturnsTip(){
        //Arrange
        double amount = 0;
        Tip tip = new Tip().setId(1L);
        when(tipRepository.save(tip)).thenAnswer(i->i.getArguments()[0]);

        //Act
        Tip foundTip = tipService.createTip(tip);

        //Assert
        assertThat(foundTip).isEqualTo(tip);
    }

}
