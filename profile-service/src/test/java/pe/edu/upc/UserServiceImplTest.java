package pe.edu.upc;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.edu.upc.entities.User;
import pe.edu.upc.repositories.ProfileRepository;
import pe.edu.upc.repositories.UserRepository;
import pe.edu.upc.services.ProfileService;
import pe.edu.upc.services.UserService;
import pe.edu.upc.services.impls.UserServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ProfileRepository profileRepository;

    @MockBean
    private ProfileService profileService;
    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration{
        @Bean
        public UserService userService(){return new UserServiceImpl();}
    }

    @Test
    @DisplayName("When createdUser With Valid Data Then Returns User")
    public void whenCreatedUserWithValidDataThenReturnUser(){
        //Arrange
        String email = "string@gmail.com";
        String password = "12345";
        User user = new User().setId(1L).setEmail(email).setPassword(password);
        when(userRepository.save(user)).thenAnswer(i->i.getArguments()[0]);

        //Act
        User foundUser = userService.createdUser(user);

        //Assert
        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    @DisplayName("When getById With Valid Id Then Returns User")
    public void whenGetByIdWithValidIdThenReturnUser() throws Exception {
        //Arrange
        String email = "string@gmail.com";
        String password = "12345";
        User user = new User().setId(1L).setEmail(email).setPassword(password);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //Act
        User foundUser = userService.findById(1L);

        //Assert
        assertThat(foundUser).isEqualTo(user);
    }

/*
    @Test
    @DisplayName("When createdUser With A Email That Is In Use Then Returns Message")
    public void whenCreatedUserWithAEmailThatIsInUseThenReturnsMessage(){
        //Arrange
        String email = "string@gmail.com";
        String password = "12345";
        String message = "There is already another user with the same email address";
        User user = new User().setId(1L).setEmail(email).setPassword(password);
        User user2 = new User().setId(2L).setEmail(email).setPassword(password);
        when(userRepository.save(user)).thenAnswer(i->i.getArguments()[0]);
        when(userService.createdUser(user2)).thenAnswer(i->i.getArguments()[0]);

        //Act
        User firstUser = userService.createdUser(user);
        Throwable exception= catchThrowable(()->{
            User secondUser=userService.createdUser(user2);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(message);

    }
*/

}
