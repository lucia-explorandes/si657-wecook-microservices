package pe.edu.upc.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.entities.Profile;
import pe.edu.upc.entities.User;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.services.ProfileService;
import pe.edu.upc.services.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> fetchAll() {
        try {
            List<User> users = userService.findAll();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> fetchById(@PathVariable("userId") Long userId) {
        try {
            User user = userService.findById(userId);
            if (user!=null) {
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        User userDB = userService.createdUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDB);
    }


    @PostMapping("/{userId}/profiles")
    public ResponseEntity<Profile> createProfile(@Valid @RequestBody Profile profile,@PathVariable("userId") Long userId, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        Profile profileDB = profileService.createProfile(userId,profile);

        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }

    @PutMapping(value = "{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") long userId, @RequestBody User user) throws Exception {
        log.info("Actualizando Usuario con Id {}", userId);
        User currentUser = userService.update(user, userId);
        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping(value = "{userId}")
    public void deleteUser(@PathVariable("userId") long userId) throws Exception {
        log.info("Eliminando User con Id {}", userId);
        userService.deleteById(userId);
    }


    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
