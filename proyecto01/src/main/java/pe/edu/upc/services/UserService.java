package pe.edu.upc.services;

import pe.edu.upc.entities.User;

public interface UserService extends CrudService<User,Long>  {


    User createdUser(User user);

    User getUserByEmail(String email);

}
