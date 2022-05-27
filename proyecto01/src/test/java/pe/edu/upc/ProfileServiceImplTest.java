package pe.edu.upc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.edu.upc.entities.Profile;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.repositories.FollowRepository;
import pe.edu.upc.repositories.ProfileRepository;
import pe.edu.upc.repositories.UserRepository;
import pe.edu.upc.services.ProfileService;
import pe.edu.upc.services.impls.ProfileServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProfileServiceImplTest {
    @MockBean
    private ProfileRepository profileRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private FollowRepository followRepository;

    @Autowired
    private ProfileService profileService;

    @TestConfiguration
    static class ProfileServiceImplTestConfiguration{
        @Bean
        public ProfileService profileService(){return new ProfileServiceImpl();
        }
    }

    @Test
    @DisplayName("When getProfileById With Valid Name Then Returns Profile")
    public void whenGetProfileByNameWithValidIdThenReturnsProfile(){
        //Arrange
        String name="Perfil1";
        Profile profile = new Profile().setId(1L).setName(name);
        when(profileRepository.findProfileByName(name)).thenReturn(Optional.of(profile));

        //Act
        Profile profileFound = profileService.getProfileByName(name);

        //Assert
        assertThat(profileFound.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When getProfileById With Invalid Id Then Returns Message")
    public void whenGetProfileByIdWithInvalidIdThenReturnsMessage(){
        //Arrange
        String name="Perfil1";
        String template="Resource %s nor found for %s with value %s";
        Profile profile = new Profile().setId(1L).setName(name);
        when(profileRepository.findById(1L)).thenReturn(Optional.empty());
        String expectedMessage= String.format(template,"Profile","Id",1L);

        //Act
        Throwable exception= catchThrowable(()->{
            Profile foundProfile=profileService.findById(1L);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
